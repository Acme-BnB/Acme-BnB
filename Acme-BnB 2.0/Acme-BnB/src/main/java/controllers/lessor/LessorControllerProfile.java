package controllers.lessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LessorService;
import services.PropertyService;
import services.RequestService;
import domain.Lessor;
import domain.Property;
import domain.Request;

@Controller
@RequestMapping("/lessor")
public class LessorControllerProfile {
	//Services-------------------------

		@Autowired
		private LessorService	lessorService;
		
		@Autowired
		private PropertyService	propertyService;

		@Autowired
		private RequestService	requestService;



		//Constructor----------------------

		public LessorControllerProfile() {
			super();
		}

		//List--------------------------

		@RequestMapping(value="/display", method=RequestMethod.GET)
		public ModelAndView display(@RequestParam int propertyId) {
				ModelAndView result;
				Property property;
				Lessor lessor;
				
				property = propertyService.findOne(propertyId);
				lessor=property.getLessor();
				result=new ModelAndView("lessor/display");
				result.addObject("lessor", lessor);
				result.addObject("comments", lessor.getcomments());
				return result;
			}
		
		@RequestMapping(value="/displayL", method=RequestMethod.GET)
		public ModelAndView display() {
				ModelAndView result;
				Lessor lessor;
				
				lessor = lessorService.findByPrincipal();
				result=new ModelAndView("lessor/display");
				result.addObject("lessor", lessor);
				result.addObject("comments", lessor.getcomments());
				return result;
			}

		@RequestMapping(value="/displayByReq", method=RequestMethod.GET)
		public ModelAndView displayByReq(@RequestParam int requestId) {
				ModelAndView result;
				Request r;
				Lessor lessor;
				r=requestService.findOne(requestId);
				lessor=r.getProperty().getLessor();
				result=new ModelAndView("lessor/display");
				result.addObject("lessor", lessor);
				result.addObject("comments", lessor.getcomments());
				return result;
			}
}
