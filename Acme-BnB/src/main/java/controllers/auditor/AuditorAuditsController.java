package controllers.auditor;

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

import services.AuditService;
import services.AuditorService;
import services.PropertyService;
import controllers.AbstractController;
import domain.Audit;
import domain.Auditor;
import domain.Property;
import forms.AuditForm;


@Controller
@RequestMapping("/auditor/audit")
public class AuditorAuditsController extends AbstractController{
	
	//Services-------------------------------------------

	@Autowired
	private AuditService auditService;
	
	@Autowired
	private AuditorService auditorService;
	
	@Autowired
	private PropertyService propertyService;


//Constructor----------------------------------------
	
	public AuditorAuditsController(){
		super();
		
	}
//Browse---------------------------------------------
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Audit> audits;
		Auditor auditor;
		
		auditor= auditorService.findByPrincipal();
		audits = auditService.findByCreator(auditor);
		
		
		result=new ModelAndView("audit/list");
		result.addObject("audits", audits);
		result.addObject("requestURI", "auditor/audit/list.do");
		return result;
	}
	//Creation-------------------------

			@RequestMapping(value = "/create", method = RequestMethod.GET)
			public ModelAndView create(@RequestParam int propertyId) {

				ModelAndView result=new ModelAndView();
				AuditForm auditForm;
				Property p=propertyService.findOne(propertyId);
				Auditor auditor=auditorService.findByPrincipal();
				if(auditor.getAudits().size()>=1){
					for(Audit a:auditor.getAudits()){
						if(a.getProperty().equals(p)){
							result=list();
							break;
						}else{
							auditForm = auditService.generateForm();
							auditForm.setPropertyId(propertyId);
							result = createEditModelAndView(auditForm, null);
					}
					
					}
				}else{
					auditForm = auditService.generateForm();
					auditForm.setPropertyId(propertyId);
					result = createEditModelAndView(auditForm, null);
				}
				return result;

			}
			//Edit--------------------------------------------------------------------------
			@RequestMapping(value = "/edit", method = RequestMethod.GET)
			public ModelAndView edit(@RequestParam int auditId) {

				ModelAndView result;
				Audit audit;

				audit = auditService.findOne(auditId);
				AuditForm auditForm=auditService.transform(audit);
				Assert.notNull(audit);
				result = new ModelAndView("audit/edit");
				result.addObject("auditForm", auditForm);

				return result;

			}
		// Save --------------------------------
			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
			public ModelAndView save(@Valid AuditForm auditForm, BindingResult binding) {

				ModelAndView result =  new ModelAndView();	
				Audit audit;
				
				if (binding.hasErrors()) {
					result = createEditModelAndView(auditForm);
				} else {
					try {
						audit = auditService.reconstruct(auditForm, binding);
						auditService.save(audit);
						result = list();
					} catch (Throwable oops) {
						String msgCode = "audit.register.error";
						result = createEditModelAndView(auditForm, msgCode); 
						}
					}
				
				return result;
			}
			// Accept -------------------------------------
			@RequestMapping(value="/final", method=RequestMethod.GET)
			public ModelAndView accept(@RequestParam int auditId) {
					ModelAndView result;
					Audit audit;
					audit = auditService.findOne(auditId);
					audit.setDraft(false);
					audit = auditService.save(audit);
					result=list();
					return result;
			}
			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
			public ModelAndView delete(AuditForm auditForm, BindingResult binding) {

				ModelAndView result=new ModelAndView();
				Audit audit;
				audit = auditService.reconstruct(auditForm, binding);
				if (binding.hasErrors()) {
					result.addObject("audit", audit);
				} else {
					try {
						auditService.delete(audit);
						result = list();
					} catch (Throwable oops) {
						result.addObject("audit", audit);
					}
				}
				return result;
			}
			//Ancillary Methods---------------------------
			protected ModelAndView createEditModelAndView(Audit audit, String message) {
				ModelAndView result;

				result = new ModelAndView("audit/edit");
				result.addObject("audit", audit);
				result.addObject("message", message);
				return result;

			}
			protected ModelAndView createEditModelAndView(AuditForm auditForm) {

				ModelAndView result;

				result = createEditModelAndView(auditForm, null);

				return result;
			}

			protected ModelAndView createEditModelAndView(@Valid AuditForm auditForm, String message) {
				ModelAndView result;

				result = new ModelAndView("audit/edit");
				result.addObject("auditForm", auditForm);

				result.addObject("message", message);

				return result;

			}

}
