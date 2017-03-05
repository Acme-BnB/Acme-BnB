
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.VatService;
import controllers.AbstractController;
import domain.Vat;
import forms.VatForm;

@Controller
@RequestMapping("/administrator/vat")
public class AdministratorVatController extends AbstractController {

	// Services ---------------------------------------------

	@Autowired
	private VatService	vatService;


	// Constructor ------------------------------------------

	public AdministratorVatController() {
		super();
	}

	// Edit personal data ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Vat vat = vatService.findOne(2);

		VatForm vatForm = vatService.generateForm(vat);

		result = new ModelAndView("vat/edit");
		result.addObject("vatForm", vatForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid VatForm vatForm, BindingResult binding) {
		ModelAndView result;
		Vat vat;

		if (binding.hasErrors()) {
			result = createEditModelAndView(vatForm);
		} else {
			try {
				vat = vatService.reconstruct(vatForm, binding);
				vatService.save(vat);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "lessor.register.error";
				result = createEditModelAndView(vatForm, msgCode);
			}
		}

		return result;
	}

	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(VatForm vatForm) {
		ModelAndView result;

		result = createEditModelAndView(vatForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(VatForm vatForm, String message) {
		ModelAndView result;

		result = new ModelAndView("vat/edit");
		result.addObject("feeForm", vatForm);
		result.addObject("message", message);

		return result;

	}

}
