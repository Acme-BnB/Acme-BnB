
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
import services.LessorService;
import domain.Lessor;
import forms.LessorForm;

@Controller
@RequestMapping("/lessor")
public class LessorRegisterController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private LessorService	lessorService;


	// Constructor ---------------------------------------------

	public LessorRegisterController() {
		super();
	}

	// Creation ------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create(@Valid @ModelAttribute Credentials credentials) {
		ModelAndView result;
		LessorForm lessorForm;

		lessorForm = lessorService.generateForm();
		result = createEditModelAndView(lessorForm);
		result.addObject("credentials", credentials);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid LessorForm lessorForm, BindingResult binding) {
		ModelAndView result;
		Lessor lessor;
		Credentials credentials = new Credentials();
		if (binding.hasErrors()) {
			result = createEditModelAndView(lessorForm);
		} else {
			try {
				lessor = lessorService.reconstruct(lessorForm);
				lessorService.save(lessor);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				String msgCode = "lessor.register.error";
				if (oops.getMessage().equals("notEqualPassword")) {
					msgCode = "lessor.register.notEqualPassword";
				} else {
					if (oops.getMessage().equals("agreedNotAccepted")) {
						msgCode = "lessor.register.agreedNotAccepted";
					}
				}
				result = createEditModelAndView(lessorForm, msgCode, credentials);
			}
		}

		return result;

	}

	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(LessorForm lessorForm) {
		ModelAndView result;
		Credentials credentials = new Credentials();

		result = createEditModelAndView(lessorForm, null, credentials);

		return result;

	}

	protected ModelAndView createEditModelAndView(LessorForm lessorForm, String message, @Valid @ModelAttribute Credentials credentials) {
		ModelAndView result;

		result = new ModelAndView("lessor/register");
		result.addObject("lessorForm", lessorForm);
		result.addObject("message", message);
		result.addObject("credentials", credentials);

		return result;
	}

}
