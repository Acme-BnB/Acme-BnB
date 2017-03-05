
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView create() {
		ModelAndView result;
		AuditorForm auditorForm;

		auditorForm = auditorService.generateForm();
		result = createEditModelAndView(auditorForm);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AuditorForm auditorForm, BindingResult binding) {
		ModelAndView result;
		Auditor auditor;

		if (binding.hasErrors()) {
			result = createEditModelAndView(auditorForm);
		} else {
			try {
				auditor = auditorService.reconstruct(auditorForm, binding);
				auditorService.save(auditor);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "auditor.register.error";
				if (oops.getMessage().equals("notEqualPassword")) {
					msgCode = "auditor.register.notEqualPassword";
				} else {
					if (oops.getMessage().equals("agreedNotAccepted")) {
						msgCode = "auditor.register.agreedNotAccepted";
					}
				}
				result = createEditModelAndView(auditorForm, msgCode);
			}
		}

		return result;

	}

	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(AuditorForm auditorForm) {
		ModelAndView result;

		result = createEditModelAndView(auditorForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(AuditorForm auditorForm, String message) {
		ModelAndView result;

		result = new ModelAndView("auditor/register");
		result.addObject("auditorForm", auditorForm);
		result.addObject("message", message);

		return result;

	}

}
