package controllers.lessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LessorService;
import services.PropertyService;
import domain.Lessor;
import domain.Property;

@Controller
@RequestMapping("/lessor")
public class LessorController {
	//Services-------------------------

		@Autowired
		private LessorService	lessorService;
		
		@Autowired
		private PropertyService	propertyService;



		//Constructor----------------------

		public LessorController() {
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
}
