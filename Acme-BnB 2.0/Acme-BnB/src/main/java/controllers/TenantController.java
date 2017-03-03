
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.TenantService;
import domain.Tenant;
import forms.TenantForm;

@Controller
@RequestMapping("/tenant")
public class TenantController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private TenantService	tenantService;


	// Constructor ---------------------------------------------

	public TenantController() {
		super();
	}

	// Creation ------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		TenantForm tenantForm;

		tenantForm = tenantService.generateForm();
		result = createEditModelAndView(tenantForm);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid TenantForm tenantForm, BindingResult binding) {
		ModelAndView result;
		Tenant tenant;

		if (binding.hasErrors()) {
			result = createEditModelAndView(tenantForm);
		} else {
			try {
				tenant = tenantService.reconstruct(tenantForm, binding);
				tenantService.save(tenant);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				String msgCode = "tenant.register.error";
				if (oops.getMessage().equals("notEqualPassword")) {
					msgCode = "tenant.register.notEqualPassword";
				} else {
					if (oops.getMessage().equals("agreedNotAccepted")) {
						msgCode = "tenant.register.agreedNotAccepted";
					}
				}
				result = createEditModelAndView(tenantForm, msgCode);
			}
		}

		return result;

	}

	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(TenantForm tenantForm) {
		ModelAndView result;

		result = createEditModelAndView(tenantForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(TenantForm tenantForm, String message) {
		ModelAndView result;

		result = new ModelAndView("tenant/register");
		result.addObject("tenantForm", tenantForm);
		result.addObject("message", message);

		return result;

	}

}
