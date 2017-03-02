package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import repositories.RequestRepository;
import services.LessorService;
import services.PropertyService;
import services.RequestService;
import domain.Lessor;
import domain.Request;

@Controller
@RequestMapping("/request")
public class RequestController {
	//Services-------------------------

		@Autowired
		private RequestService	requestService;
		
		@Autowired
		private PropertyService	propertyService;
		
		@Autowired
		private LessorService	lessorService;



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
}
