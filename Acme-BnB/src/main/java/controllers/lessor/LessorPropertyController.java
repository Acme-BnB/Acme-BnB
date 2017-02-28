
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

import services.LessorService;
import services.PropertyService;
import controllers.AbstractController;
import domain.Lessor;
import domain.Property;

@Controller
@RequestMapping("/lessor/property")
public class LessorPropertyController extends AbstractController {

	//Services-------------------------

	@Autowired
	private PropertyService	propertyService;

	@Autowired
	private LessorService	lessorService;


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

		result = new ModelAndView("tenant/list");
		result.addObject("properties", properties);
		result.addObject("requestURI", "tenant/property/list.do");

		return result;
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Property property;

		property = propertyService.create();
		result = createEditModelAndView(property);

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int propertyId) {

		ModelAndView result;
		Property property;

		property = propertyService.findOne(propertyId);
		Assert.notNull(property);
		result = createEditModelAndView(property);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Property property, BindingResult binding) {

		ModelAndView result;
		Lessor lessor = lessorService.findByPrincipal();

		if (binding.hasErrors() || property.getLessor() != lessor) {
			result = createEditModelAndView(property);
		} else {
			try {
				propertyService.save(property);
				result = list();
			} catch (Throwable oops) {
				result = createEditModelAndView(property, "master.page.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Property property, BindingResult binding) {

		ModelAndView result;

		try {
			propertyService.delete(property);
			result = list();
		} catch (Throwable oops) {
			result = createEditModelAndView(property, "master.page.commit.error");
		}
		return result;
	}

	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(Property property) {

		ModelAndView result;

		result = createEditModelAndView(property, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Property property, String message) {
		ModelAndView result;

		result = new ModelAndView("property/edit");
		result.addObject("property", property);

		result.addObject("message", message);

		return result;

	}

}
