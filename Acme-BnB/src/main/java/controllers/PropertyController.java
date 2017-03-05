package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Property;
import domain.Request;

import services.PropertyService;
import services.RequestService;

@Controller
@RequestMapping("/property")
public class PropertyController extends AbstractController{
	//Services-------------------------------------------

		@Autowired
		private PropertyService propertyService;
		
		@Autowired
		private RequestService requestService;
		//Constructor----------------------------------------
		
		public PropertyController(){
			super();
			
		}
		//Browse---------------------------------------------
		
		@RequestMapping(value="/browse", method=RequestMethod.GET)
		public ModelAndView browse(){
			ModelAndView result;
			Collection<Property>properties;
			properties=propertyService.findAll();
			
			
			result= new ModelAndView("property/browse");
			result.addObject("properties",properties);
			result.addObject("requestURI", "property/browse.do");
			return result;
		}
		//Browse---------------------------------------------
		
		@RequestMapping(value="/browseByReq", method=RequestMethod.GET)
		public ModelAndView browseByReq(){
				ModelAndView result;
				Collection<Property>properties;
				properties=propertyService.orderByNumRequest();
				result= new ModelAndView("property/browse");
				result.addObject("properties",properties);
				result.addObject("requestURI", "property/browse.do");
				return result;
				}
		
		@RequestMapping(value="/display", method=RequestMethod.GET)
		public ModelAndView display(@RequestParam int propertyId) {
				ModelAndView result;
				Property property;
				property = propertyService.findOne(propertyId);
				result=new ModelAndView("property/display");
				result.addObject("property", property);
				return result;
			}
		
		@RequestMapping(value="/displayByRequest", method=RequestMethod.GET)
		public ModelAndView displayByRequest(@RequestParam int requestId) {
				ModelAndView result;
				Property property;
				Request request=requestService.findOne(requestId);
				property=request.getProperty();
				
				result=new ModelAndView("property/display");
				result.addObject("property", property);
				return result;
			}

}
