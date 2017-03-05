
package controllers.administrator;

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
import controllers.AbstractController;
import domain.Attribute;
import forms.AttributeForm;


@Controller
@RequestMapping("/administrator/attribute")
public class AdministratorAttributeController extends AbstractController {

	//Services-------------------------

	@Autowired
	private AttributeService	attributeService;


	//Constructor----------------------

	public AdministratorAttributeController() {
		super();
	}

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Attribute> attributes;

		attributes = attributeService.findAll();

		result = new ModelAndView("attribute/list");
		result.addObject("attributes", attributes);
		result.addObject("requestURI", "administrator/attribute/list.do");

		return result;
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		AttributeForm attributeForm;

		attributeForm = attributeService.generateForm();
		result = createEditModelAndView(attributeForm,null);

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int attributeId) {

		ModelAndView result;
		Attribute attribute;

		attribute = attributeService.findOne(attributeId);
		Assert.notNull(attribute);
		result = new ModelAndView("attribute/edit");
		result.addObject("attribute", attribute);
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Attribute attribute, BindingResult binding) {
		ModelAndView result =  new ModelAndView();		
		if (binding.hasErrors()) {
			result = createEditModelAndView(attribute);
		} else {
			try {
				attribute = attributeService.reconstruct(attribute, binding);
				attributeService.save2(attribute);
				result = list();
			} catch (Throwable oops) {
				String msgCode = "administrator.register.error";
				result = createEditModelAndView(attribute, msgCode); 
				}
			}
		
		return result;
	
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Attribute attribute, BindingResult binding) {

		ModelAndView result;
		
		attribute = attributeService.reconstruct(attribute, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(attribute);
		} else {
			try {
				attributeService.delete(attribute);
				result = list();
			} catch (Throwable oops) {
				result = createEditModelAndView(attribute);
			}
		}
		return result;
	}

	

	
	//Ancillary Methods---------------------------


		protected ModelAndView createEditModelAndView(Attribute attribute, String message) {
			ModelAndView result;

			result = new ModelAndView("attribute/edit");
			result.addObject("attribute", attribute);
			result.addObject("message", message);
			return result;

		}
		
		protected ModelAndView createEditModelAndView(AttributeForm attribute, String message) {
			ModelAndView result;

			result = new ModelAndView("attribute/edit");
			result.addObject("attribute", attribute);

			result.addObject("message", message);

			return result;

		}
		
		protected ModelAndView createEditModelAndView(Attribute attribute) {
			ModelAndView result;

			result = createEditModelAndView(attribute, null);

			return result;

		}

}
