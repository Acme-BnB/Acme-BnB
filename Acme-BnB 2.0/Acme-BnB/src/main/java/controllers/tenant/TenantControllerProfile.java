
package controllers.tenant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TenantService;
import domain.Tenant;
import forms.TenantForm;

@Controller
@RequestMapping("/tenant")
public class TenantControllerProfile {

	//Services-------------------------

	@Autowired
	private TenantService	tenantService;


	//Constructor----------------------

	public TenantControllerProfile() {
		super();
	}

	//List--------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int tenantId) {
		ModelAndView result;
		Tenant tenant;

		tenant = tenantService.findOne(tenantId);

		result = new ModelAndView("tenant/display");
		result.addObject("tenant", tenant);
		result.addObject("comments", tenant.getcomments());
		return result;
	}

	// Edit personal data ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Tenant tenant = tenantService.findByPrincipal();
		String tipe = "personal";

		TenantForm tenantForm = tenantService.generateForm(tenant);

		result = new ModelAndView("tenant/register");
		result.addObject("tenantForm", tenantForm);
		result.addObject("tipe", tipe);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid TenantForm tenantForm, BindingResult binding) {
		ModelAndView result;
		Tenant tenant;

		if (binding.hasErrors()) {
			result = createEditModelAndView(tenantForm);
		} else {
			try {
				tenant = tenantService.reconstructEditPersonalData(tenantForm, binding);
				tenantService.save2(tenant);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "lessor.register.error";
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
