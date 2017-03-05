
package controllers.auditor;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuditorService;
import controllers.AbstractController;
import domain.Auditor;
import forms.AuditorForm;

@Controller
@RequestMapping("/auditor")
public class AuditorProfileController extends AbstractController {

	//Services-------------------------

	@Autowired
	private AuditorService	auditorService;


	//Constructor----------------------

	public AuditorProfileController() {
		super();
	}

	// Edit personal data ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Auditor auditor = auditorService.findByPrincipal();
		String tipe = "personal";

		AuditorForm auditorForm = auditorService.generateForm(auditor);

		result = new ModelAndView("auditor/register");
		result.addObject("auditorForm", auditorForm);
		result.addObject("tipe", tipe);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AuditorForm auditorForm, BindingResult binding) {
		ModelAndView result;
		Auditor auditor;

		if (binding.hasErrors()) {
			result = createEditModelAndView(auditorForm);
		} else {
			try {
				auditor = auditorService.reconstructEditPersonalData(auditorForm, binding);
				auditorService.save2(auditor);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "lessor.register.error";
				result = createEditModelAndView(auditorForm, msgCode);
			}
		}

		return result;
	}

	@RequestMapping(value = "/displayA", method = RequestMethod.GET)
	public ModelAndView display() {
		  ModelAndView result;
		  Auditor auditor;
	
		  auditor = auditorService.findByPrincipal();
		  result = new ModelAndView("auditor/display");
		  result.addObject("auditor", auditor);
		  result.addObject("socialIdentities", auditor.getSocialIdentities());

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
