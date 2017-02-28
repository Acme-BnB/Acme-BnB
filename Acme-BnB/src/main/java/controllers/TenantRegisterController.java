
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
import services.TenantService;
import domain.Tenant;
import forms.TenantForm;

@Controller
@RequestMapping("/tenant")
public class TenantRegisterController extends AbstractController {

	// Services ------------------------------------------------

	@Autowired
	private TenantService	tenantService;


	// Constructor ---------------------------------------------

	public TenantRegisterController() {
		super();
	}

	// Creation ------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create(@Valid @ModelAttribute Credentials credentials) {
		ModelAndView result;
		TenantForm tenantForm;

		tenantForm = tenantService.generateForm();
		result = createEditModelAndView(tenantForm);
		result.addObject("credentials", credentials);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid TenantForm tenantForm, BindingResult binding) {
		ModelAndView result;
		Tenant tenant;
		Credentials credentials = new Credentials();
		if (binding.hasErrors()) {
			result = createEditModelAndView(tenantForm);
		} else {
			try {
				tenant = tenantService.reconstruct(tenantForm);
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
				result = createEditModelAndView(tenantForm, msgCode, credentials);
			}
		}

		return result;

	}

	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(TenantForm tenantForm) {
		ModelAndView result;
		Credentials credentials = new Credentials();

		result = createEditModelAndView(tenantForm, null, credentials);

		return result;

	}

	protected ModelAndView createEditModelAndView(TenantForm tenantForm, String message, @Valid @ModelAttribute Credentials credentials) {
		ModelAndView result;

		result = new ModelAndView("tenant/register");
		result.addObject("tenantForm", tenantForm);
		result.addObject("message", message);
		result.addObject("credentials", credentials);

		return result;

	}

}
