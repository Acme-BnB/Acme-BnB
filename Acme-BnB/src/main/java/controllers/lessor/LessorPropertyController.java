
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
import services.PropertyService;
import controllers.AbstractController;
import domain.Property;
import forms.PropertyForm;

@Controller
@RequestMapping("/lessor/property")
public class LessorPropertyController extends AbstractController {

	//Services-------------------------

	@Autowired
	private PropertyService	propertyService;



	//Constructor----------------------

	public LessorPropertyController() {
		super();
	}

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Property> properties;

		properties = propertyService.findByUserAccount();

		result = new ModelAndView("property/list");
		result.addObject("properties", properties);
		result.addObject("requestURI", "lessor/property/list.do");

		return result;
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		PropertyForm propertyForm;

		propertyForm = propertyService.generateForm();
		result = createEditModelAndView(propertyForm, null);

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int propertyId) {

		ModelAndView result;
		Property property;

		property = propertyService.findOne(propertyId);
		//PropertyForm propertyForm = propertyService.transform(property);
		Assert.notNull(property);
		result = new ModelAndView("property/edit");
		result.addObject("property", property);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Property property, BindingResult binding) {

		ModelAndView result =  new ModelAndView();		
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(property);
		} else {
			try {
				property = propertyService.reconstruct(property, binding);
				propertyService.save2(property);
				result = list();
			} catch (Throwable oops) {
				String msgCode = "lessor.register.error";
				if (oops.getMessage().equals("nullRate")) {
					msgCode = "lessor.property.nullRate";
					result = createEditModelAndView(property, msgCode); 
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Property property, BindingResult binding) {

		ModelAndView result;
		
		property = propertyService.reconstruct(property, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(property);
		} else {
			try {
				propertyService.delete(property);
				result = list();
			} catch (Throwable oops) {
				result = createEditModelAndView(property);
			}
		}
		return result;
	}

	//Ancillary Methods---------------------------


	protected ModelAndView createEditModelAndView(Property property, String message) {
		ModelAndView result;

		result = new ModelAndView("property/edit");
		result.addObject("property", property);
		result.addObject("message", message);
		return result;

	}
	
	protected ModelAndView createEditModelAndView(PropertyForm property, String message) {
		ModelAndView result;

		result = new ModelAndView("property/edit");
		result.addObject("property", property);

		result.addObject("message", message);

		return result;

	}
	
	protected ModelAndView createEditModelAndView(Property property) {
		ModelAndView result;

		result = createEditModelAndView(property, null);

		return result;

	}

}
