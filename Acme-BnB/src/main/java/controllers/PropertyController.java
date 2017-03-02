package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Property;

import services.PropertyService;

@Controller
@RequestMapping("/property")
public class PropertyController extends AbstractController{
	//Services-------------------------------------------

		@Autowired
		private PropertyService propertyService;
		
		
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
		

}
