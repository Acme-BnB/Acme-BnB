package controllers.lessor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AttributeService;
import services.PropertyService;
import services.ValueService;

import controllers.AbstractController;
import domain.Attribute;
import domain.Property;
import domain.Value;
import forms.ValueForm;

@Controller
@RequestMapping("/lessor/value")
public class LessorValueController extends AbstractController{
	//Services-------------------------
		
		@Autowired
		private ValueService valueService;
		
		@Autowired
		private AttributeService attributeService;

	//Constructor----------------------

	public LessorValueController() {
		super();
	}
	
	//Creation-------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int propertyId) {

			ModelAndView result;
			ValueForm valueForm;
			Collection<Attribute> attributes;

			attributes = attributeService.findAll();
			valueForm = valueService.generateForm();
			valueForm.setPropertyId(propertyId);
			
			
			result = createEditModelAndView(valueForm, null);
			result.addObject("attributes", attributes);
			return result;

		}

/*		//Edition--------------------------

		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int propertyId) {

			ModelAndView result;
			Property property;

			property = propertyService.findOne(propertyId);
			//PropertyForm propertyForm = propertyService.transform(property);
			Assert.notNull(property);
			result = new ModelAndView("value/edit");
			result.addObject("property", property);

			return result;

		}
*/	
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid ValueForm valueForm, BindingResult binding) {

			ModelAndView result =  new ModelAndView();		
			Value value;
			Property property;
			
			if (binding.hasErrors()) {
				result = createEditModelAndView(valueForm, null);
			} else {
				try {
					value = valueService.reconstruct(valueForm, binding);
					property = value.getProperty();
					for(Value v: property.getValues()){
						Assert.isTrue(v.getAttribute().equals(value.getAttribute()), "duplicate");
					}
					
					valueService.save(value);
					result = new ModelAndView("redirect:../../property/display.do?propertyId="+value.getProperty().getId());
				} catch (Throwable oops) {
					String msgCode;
					if (oops.getMessage().equals("duplicate")) {
						msgCode = "value.duplicate";
						result = createEditModelAndView(valueForm, msgCode); 
					}
				}
			}
			return result;
		}

		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public ModelAndView delete(@RequestParam int valueId) {

			ModelAndView result;
			Value value;
			int propertyId;
			
			value = valueService.findOne(valueId);
			propertyId=value.getProperty().getId();
			valueService.delete(value);
			result = new ModelAndView("redirect:../../property/display.do?propertyId="+propertyId);

			return result;
		}

		//Ancillary Methods---------------------------


		protected ModelAndView createEditModelAndView(Value value, String message) {
			ModelAndView result;

			result = new ModelAndView("value/edit");
			result.addObject("value", value);
			result.addObject("message", message);
			return result;

		}
		
		protected ModelAndView createEditModelAndView(ValueForm valueForm, String message) {
			ModelAndView result;
			Collection<Attribute> attributes;
			attributes = attributeService.findAll();
		
			result = new ModelAndView("value/edit");
			result.addObject("valueForm", valueForm);
			result.addObject("attributes", attributes);
			
			result.addObject("message", message);

			return result;

		}
		
		protected ModelAndView createEditModelAndView(Value value) {
			ModelAndView result;
			
			Collection<Attribute> attributes;
			attributes = attributeService.findAll();

			result = createEditModelAndView(value, null);
			result.addObject("attributes", attributes);
			return result;

		}

}
