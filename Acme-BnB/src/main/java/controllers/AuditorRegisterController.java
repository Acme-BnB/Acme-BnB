
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
import services.AuditorService;
import domain.Auditor;
import forms.AuditorForm;

@Controller
@RequestMapping("/auditor")
public class AuditorRegisterController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private AuditorService	auditorService;


	// Constructor ---------------------------------------------

	public AuditorRegisterController() {
		super();
	}

	// Creation ------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create(@Valid @ModelAttribute Credentials credentials) {
		ModelAndView result;
		AuditorForm auditorForm;

		auditorForm = auditorService.generateForm();
		result = createEditModelAndView(auditorForm);
		result.addObject("credentials", credentials);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AuditorForm auditorForm, BindingResult binding) {
		ModelAndView result;
		Auditor auditor;
		Credentials credentials = new Credentials();
		if (binding.hasErrors()) {
			result = createEditModelAndView(auditorForm);
		} else {
			try {
				auditor = auditorService.reconstruct(auditorForm);
				auditorService.save(auditor);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				String msgCode = "auditor.register.error";
				if (oops.getMessage().equals("notEqualPassword")) {
					msgCode = "auditor.register.notEqualPassword";
				} else {
					if (oops.getMessage().equals("agreedNotAccepted")) {
						msgCode = "auditor.register.agreedNotAccepted";
					}
				}
				result = createEditModelAndView(auditorForm, msgCode, credentials);
			}
		}

		return result;

	}

	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(AuditorForm auditorForm) {
		ModelAndView result;
		Credentials credentials = new Credentials();

		result = createEditModelAndView(auditorForm, null, credentials);

		return result;

	}

	protected ModelAndView createEditModelAndView(AuditorForm auditorForm, String message, @Valid @ModelAttribute Credentials credentials) {
		ModelAndView result;

		result = new ModelAndView("auditor/register");
		result.addObject("auditorForm", auditorForm);
		result.addObject("message", message);
		result.addObject("credentials", credentials);

		return result;

	}

}
