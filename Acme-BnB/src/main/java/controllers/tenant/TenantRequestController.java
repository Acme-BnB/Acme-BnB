package controllers.tenant;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Request;
import domain.Tenant;
import forms.RequestForm;

import services.RequestService;
import services.TenantService;

@Controller
@RequestMapping("/tenant/request")
public class TenantRequestController {

	// Services --------------------------
	
	@Autowired
	private RequestService	requestService;
	
	@Autowired
	private TenantService	tenantService;
	
	
	// Constructor -----------------------
	
	public TenantRequestController(){
		super();
	}
	
	//Browse--------------------------

	@RequestMapping(value="/browse", method=RequestMethod.GET)
	public ModelAndView browse() {
			ModelAndView result;
			Collection<Request> requests;
			Tenant tenant;
			tenant= tenantService.findByPrincipal();
			requests = requestService.findByCreator(tenant);
			
			requests = requestService.encryptCreditCard(requests);
			result=new ModelAndView("request/browse");
			result.addObject("requests", requests);
			result.addObject("tenantUsername", tenant.getUserAccount().getUsername());
			result.addObject("requestURI", "tenant/request/browse.do");
			
			return result;
		}
	
	//Creation-------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int propertyId) {

			ModelAndView result;
			RequestForm requestForm;

			requestForm = requestService.generateForm();
			requestForm.setPropertyId(propertyId);
			result = createEditModelAndView(requestForm, null);

			return result;

		}
	
	// Save --------------------------------
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid RequestForm requestForm, BindingResult binding) {

			ModelAndView result =  new ModelAndView();	
			Request request;
			
			if (binding.hasErrors()) {
				result = createEditModelAndView(requestForm);
			} else {
				try {
					request = requestService.reconstruct(requestForm, binding);
					Assert.isTrue(requestService.checkDate(request), "badDate");
					requestService.save(request);
					result = browse();
				} catch (Throwable oops) {
					String msgCode = "request.register.error";
					if (oops.getMessage().equals("badCreditCard")) {
						msgCode = "request.badCreditCard";
						result = createEditModelAndView(requestForm, msgCode); 
					}
					if (oops.getMessage().equals("badProperty")) {
						msgCode = "request.badProperty";
						result = createEditModelAndView(requestForm, msgCode); 
					}
					if (oops.getMessage().equals("badDate")) {
						msgCode = "request.badDate";
						result = createEditModelAndView(requestForm, msgCode); 
					}
				}
			}
			return result;
		}
		

		//Ancillary Methods---------------------------

		protected ModelAndView createEditModelAndView(RequestForm requestForm) {

			ModelAndView result;

			result = createEditModelAndView(requestForm, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(@Valid RequestForm requestForm, String message) {
			ModelAndView result;

			result = new ModelAndView("request/edit");
			result.addObject("requestForm", requestForm);

			result.addObject("message", message);

			return result;

		}

	
}
