
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.SocialIdentityService;
import controllers.AbstractController;
import domain.Administrator;
import domain.SocialIdentity;
import forms.AdministratorForm;

@Controller
@RequestMapping("/administrator")
public class AdministratorProfileController extends AbstractController {

	//Services-------------------------

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private SocialIdentityService	socialIdentityService;


	//Constructor----------------------

	public AdministratorProfileController() {
		super();
	}

	// Edit personal data ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Administrator admin = administratorService.findByPrincipal();
		String tipe = "personal";

		AdministratorForm administratorForm = administratorService.generateForm(admin);

		result = new ModelAndView("administrator/register");
		result.addObject("administratorForm", administratorForm);
		result.addObject("tipe", tipe);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AdministratorForm administratorForm, BindingResult binding) {
		ModelAndView result;
		Administrator administrator;

		if (binding.hasErrors()) {
			result = createEditModelAndView(administratorForm);
		} else {
			try {
				administrator = administratorService.reconstructEditPersonalData(administratorForm, binding);
				administratorService.save(administrator);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "lessor.register.error";
				result = createEditModelAndView(administratorForm, msgCode);
			}
		}

		return result;
	}

	@RequestMapping(value = "/displayAd", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Administrator administrator;
		Collection<SocialIdentity> socialIdentities;
		socialIdentities = socialIdentityService.findByPrincipal2();

		administrator = administratorService.findByPrincipal();
		result = new ModelAndView("administrator/display");
		result.addObject("socialIdentities", socialIdentities);
		result.addObject("administrator", administrator);
		return result;
	}

	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(AdministratorForm administratorForm) {
		ModelAndView result;

		result = createEditModelAndView(administratorForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(AdministratorForm administratorForm, String message) {
		ModelAndView result;

		result = new ModelAndView("administrator/register");
		result.addObject("administratorForm", administratorForm);
		result.addObject("message", message);

		return result;

	}

}
