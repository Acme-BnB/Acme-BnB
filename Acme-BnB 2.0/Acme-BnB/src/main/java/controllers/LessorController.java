
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.LessorService;
import domain.Lessor;
import forms.LessorForm;

@Controller
@RequestMapping("/lessor")
public class LessorController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private LessorService	lessorService;


	// Constructor ---------------------------------------------

	public LessorController() {
		super();
	}

	// Creation ------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		LessorForm lessorForm;

		lessorForm = lessorService.generateForm();
		result = createEditModelAndView(lessorForm);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid LessorForm lessorForm, BindingResult binding) {
		ModelAndView result;
		Lessor lessor;

		if (binding.hasErrors()) {
			result = createEditModelAndView(lessorForm);
		} else {
			try {
				lessor = lessorService.reconstruct(lessorForm, binding);
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
				result = createEditModelAndView(lessorForm, msgCode);
			}
		}

		return result;

	}

	// Edit ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Lessor lessor = lessorService.findByPrincipal();

		result = new ModelAndView("lessor/register");
		result.addObject("lessorForm", lessor);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Lessor lessor, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(lessor);
		} else {
			try {
				lessor = lessorService.reconstruct(lessor, binding);
				lessorService.save(lessor);
				result = new ModelAndView("redirect:welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "lessor.register.error";
				result = createEditModelAndView(lessor, msgCode);
			}
		}

		return result;
	}

	// Ancillary methods ---------------------------------------------------

	protected ModelAndView createEditModelAndView(LessorForm lessorForm) {
		ModelAndView result;

		result = createEditModelAndView(lessorForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(LessorForm lessorForm, String message) {
		ModelAndView result;

		result = new ModelAndView("lessor/register");
		result.addObject("lessorForm", lessorForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createEditModelAndView(Lessor lessor) {
		ModelAndView result;

		result = createEditModelAndView(lessor, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(Lessor lessor, String message) {
		ModelAndView result;

		result = new ModelAndView("lessor/register");
		result.addObject("lessorForm", lessor);
		result.addObject("message", message);

		return result;
	}

}
