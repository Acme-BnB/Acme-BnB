
package controllers;

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

import services.SocialIdentityService;
import domain.SocialIdentity;
import forms.SocialIdentityForm;

@Controller
@RequestMapping("/socialIdentity")
public class SocialIdentityController extends AbstractController {

	//Services-------------------------------------------

	@Autowired
	private SocialIdentityService	socialIdentityService;


	//Constructor----------------------------------------

	public SocialIdentityController() {
		super();
	}

	//List--------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<SocialIdentity> socialIdentities;

		socialIdentities = socialIdentityService.findByPrincipal2();

		result = new ModelAndView("socialIdentity/list");
		result.addObject("socialIdentities", socialIdentities);
		result.addObject("requestURI", "socialIdentity/list.do");

		return result;
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		SocialIdentityForm socialIdentityForm;

		socialIdentityForm = socialIdentityService.generateForm();
		result = createEditModelAndView(socialIdentityForm, null);

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int socialIdentityId) {

		ModelAndView result;
		SocialIdentity socialIdentity;

		socialIdentity = socialIdentityService.findOne(socialIdentityId);
		//PropertyForm propertyForm = propertyService.transform(property);
		Assert.notNull(socialIdentity);
		result = new ModelAndView("socialIdentity/edit");
		result.addObject("socialIdentity", socialIdentity);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SocialIdentity socialIdentity, BindingResult binding) {

		ModelAndView result = new ModelAndView();

		if (binding.hasErrors()) {
			result = createEditModelAndView(socialIdentity);
		} else {
			try {
				socialIdentity = socialIdentityService.reconstruct(socialIdentity, binding);
				socialIdentityService.save(socialIdentity);
				result = list();
			} catch (Throwable oops) {
				String msgCode = "socialIdentity.save.error";
				result = createEditModelAndView(socialIdentity, msgCode);
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(SocialIdentity socialIdentity, BindingResult binding) {

		ModelAndView result;

		socialIdentity = socialIdentityService.reconstruct(socialIdentity, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(socialIdentity);
		} else {
			try {
				socialIdentityService.delete(socialIdentity);
				result = list();
			} catch (Throwable oops) {
				result = createEditModelAndView(socialIdentity);
			}
		}
		return result;
	}

	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(SocialIdentity socialIdentity, String message) {
		ModelAndView result;

		result = new ModelAndView("socialIdentity/edit");
		result.addObject("socialIdentity", socialIdentity);
		result.addObject("message", message);
		return result;

	}

	protected ModelAndView createEditModelAndView(SocialIdentityForm socialIdentity, String message) {
		ModelAndView result;

		result = new ModelAndView("socialIdentity/edit");
		result.addObject("socialIdentity", socialIdentity);

		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndView(SocialIdentity socialIdentity) {
		ModelAndView result;

		result = createEditModelAndView(socialIdentity, null);

		return result;

	}

}
