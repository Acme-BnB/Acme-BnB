package controllers.auditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import services.AuditorService;


import controllers.AbstractController;
import domain.Audit;
import domain.Auditor;


@Controller
@RequestMapping("/auditor/audit")
public class AuditorAuditsController extends AbstractController{
	
	//Services-------------------------------------------

	@Autowired
	private AuditService auditService;
	
	@Autowired
	private AuditorService auditorService;


//Constructor----------------------------------------
	
	public AuditorAuditsController(){
		super();
		
	}
//Browse---------------------------------------------
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Audit>audits;
		Auditor auditor=auditorService.findByPrincipal();
		audits=auditService.findByCreator(auditor);
		result= new ModelAndView("audit/list");
		result.addObject("audits",audits);
		result.addObject("requestURI", "auditor/audit/list.do");
		return result;
	}

}
