package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Audit;
import domain.Property;

import services.AuditService;
import services.PropertyService;


@Controller
@RequestMapping("/audit")
public class AuditController extends AbstractController{
	//Services-------------------------------------------

			@Autowired
			private AuditService auditService;
			
			@Autowired
			private PropertyService propertyService;
	
	
	//Constructor----------------------------------------
			
			public AuditController(){
				super();
				
			}
	//Browse---------------------------------------------
			
			@RequestMapping(value="/browse", method=RequestMethod.GET)
			public ModelAndView browse(@RequestParam int propertyId){
				ModelAndView result;
				Collection<Audit>audits;
				Property property;
				property=propertyService.findOne(propertyId);
				audits=auditService.findNoDraftAudits(property);
				result= new ModelAndView("audit/browse");
				result.addObject("audits",audits);
				result.addObject("requestURI", "audit/browse.do");
				return result;
			}
			@RequestMapping(value="/display", method=RequestMethod.GET)
			public ModelAndView display(@RequestParam int auditId){
				ModelAndView result;
				Audit audit=auditService.findOne(auditId);
				result=new ModelAndView("audit/display");
				result.addObject("audit",audit);
				result.addObject("requestURI", "audit/display.do");
				return result;
			}
}
