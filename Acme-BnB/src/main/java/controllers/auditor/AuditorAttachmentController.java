package controllers.auditor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.AttachmentService;
import controllers.AbstractController;
import domain.Attachment;
import forms.AttachmentForm;

@Controller
@RequestMapping("/auditor/attachment")
public class AuditorAttachmentController extends AbstractController{
	
	//Services-------------------------------------------

		@Autowired
		private AttachmentService attachmentService;


	//Constructor----------------------------------------
		
		public AuditorAttachmentController(){
			super();
			
		}
		
	// Creation -------------------------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int auditId) {

			ModelAndView result;
			AttachmentForm attachmentForm;
			attachmentForm = attachmentService.generateForm(auditId);			
			
			result = createEditModelAndView(attachmentForm, null);
			return result;

		}
		
	// Save ---------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AttachmentForm attachmentForm, BindingResult binding) {

		ModelAndView result =  new ModelAndView();		
		Attachment attachment;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(attachmentForm, null);
		} else {
			try {
				attachment = attachmentService.reconstruct(attachmentForm, binding);
				attachmentService.save(attachment);
				result = new ModelAndView("audit/display");
				result.addObject("audit", attachment.getAudit());
				result.addObject("attachments", attachment.getAudit().getAttachments());
				result.addObject("requestURI", "audit/display.do");
				
			} catch (Throwable oops) {
					String msgCode = "attachment.error";
					result = createEditModelAndView(attachmentForm, msgCode); 
			}
		}
		return result;
	}
	
	// Delete -----------------------------------------------------------
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public ModelAndView delete(@RequestParam int attachmentId) {

			ModelAndView result;
			Attachment attachment;
			attachment = attachmentService.findOne(attachmentId);	
			attachmentService.delete(attachment);
			
			result = new ModelAndView("audit/display");
			result.addObject("audit", attachment.getAudit());
			result.addObject("attachments", attachment.getAudit().getAttachments());
			result.addObject("requestURI", "audit/display.do");
			return result;

		}
	
		
	//Ancillary Methods---------------------------

			protected ModelAndView createEditModelAndView(@Valid AttachmentForm attachmentForm, String message) {
				ModelAndView result;
			
				result = new ModelAndView("attachment/edit");
				result.addObject("attachmentForm", attachmentForm);
				result.addObject("message", message);

				return result;

			}

}
