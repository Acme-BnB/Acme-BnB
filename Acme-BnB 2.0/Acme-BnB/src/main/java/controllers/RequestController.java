package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FeeService;
import services.LessorService;
import services.RequestService;
import domain.Lessor;
import domain.Request;
import domain.Tenant;
import services.TenantService;

@Controller
@RequestMapping("/request")
public class RequestController {
	//Services-------------------------

		@Autowired
		private RequestService	requestService;
		
		@Autowired
		private LessorService	lessorService;
		
		@Autowired
		private FeeService feeService;

		@Autowired
		private TenantService tenantService;



		//Constructor----------------------

		public RequestController() {
			super();
		}

		//List--------------------------

		@RequestMapping(value="/list", method=RequestMethod.GET)
		public ModelAndView display() {
				ModelAndView result;
				Collection<Request> requests;
				Lessor lessor;
				
				lessor= lessorService.findByPrincipal();
				requests = lessorService.findRequestPerLessor(lessor);
				
				requests = requestService.encryptCreditCard(requests);
				result=new ModelAndView("request/list");
				result.addObject("requests", requests);
				result.addObject("lessor", lessor);
				return result;
		}
		
		// Accept -------------------------------------
		@RequestMapping(value="/accept", method=RequestMethod.GET)
		public ModelAndView accept(@RequestParam int requestId) {
				ModelAndView result;
				Collection<Request> requests;
				Lessor lessor;
				Request request;
				
				request = requestService.findOne(requestId);
				request.setStatus("ACCEPTED");
				
				
				request = requestService.save(request);
				lessor= request.getProperty().getLessor();
				lessor.setFeeAmount(lessor.getFeeAmount()+feeService.findOne(1).getValue());
				lessor = lessorService.save2(lessor);
				
				requests = lessorService.findRequestPerLessor(lessor);
				
				requests = requestService.encryptCreditCard(requests);
				result=new ModelAndView("request/list");
				result.addObject("requests", requests);
				result.addObject("lessor", lessor);
				return result;
		}
		
		// Accept -------------------------------------
				@RequestMapping(value="/deny", method=RequestMethod.GET)
				public ModelAndView deny(@RequestParam int requestId) {
						ModelAndView result;
						Collection<Request> requests;
						Lessor lessor;
						Request request;
						
						request = requestService.findOne(requestId);
						request.setStatus("DENIED");
						request = requestService.save(request);
						lessor= request.getProperty().getLessor();
						
						
						requests = lessorService.findRequestPerLessor(lessor);
						
						requests = requestService.encryptCreditCard(requests);
						result=new ModelAndView("request/list");
						result.addObject("requests", requests);
						result.addObject("lessor", lessor);
						return result;
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
						return result;
					}
}
