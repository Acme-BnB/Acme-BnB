
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Credentials;
import services.AdministratorService;
import domain.Administrator;
import forms.AdministratorForm;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AdministratorService	administratorService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Creation ------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create(@Valid @ModelAttribute Credentials credentials) {
		ModelAndView result;
		AdministratorForm administratorForm;

		administratorForm = administratorService.generateForm();
		result = createEditModelAndView(administratorForm);
		result.addObject("credentials", credentials);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AdministratorForm administratorForm, BindingResult binding) {
		ModelAndView result;
		Administrator administrator;
		Credentials credentials = new Credentials();
		if (binding.hasErrors()) {
			result = createEditModelAndView(administratorForm);
		} else {
			try {
				administrator = administratorService.reconstruct(administratorForm);
				administratorService.save2(administrator);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				String msgCode = "administrator.register.error";
				if (oops.getMessage().equals("notEqualPassword")) {
					msgCode = "administrator.register.notEqualPassword";
				} else {
					if (oops.getMessage().equals("agreedNotAccepted")) {
						msgCode = "administrator.register.agreedNotAccepted";
					}
				}
				result = createEditModelAndView(administratorForm, msgCode, credentials);
			}
		}

		return result;

	}

	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(AdministratorForm administratorForm) {
		ModelAndView result;
		Credentials credentials = new Credentials();

		result = createEditModelAndView(administratorForm, null, credentials);

		return result;

	}

	protected ModelAndView createEditModelAndView(AdministratorForm administratorForm, String message, @Valid @ModelAttribute Credentials credentials) {
		ModelAndView result;

		result = new ModelAndView("administrator/register");
		result.addObject("administratorForm", administratorForm);
		result.addObject("message", message);
		result.addObject("credentials", credentials);

		return result;
	}

}
