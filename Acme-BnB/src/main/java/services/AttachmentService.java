package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.AttachmentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Attachment;
import domain.Audit;
import forms.AttachmentForm;

@Service
@Transactional
public class AttachmentService {
	// Managed repository -----------------------------------------------------

		@Autowired
		private AttachmentRepository	attachmentRepository;


		// Supporting services ----------------------------------------------------

		@Autowired
		private AuditService	auditService;

		@Autowired
		private Validator		validator;


		
		// Constructors -----------------------------------------------------------

		public AttachmentService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------

		public Attachment create(Audit a) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("AUDITOR");
			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Attachment result;
			result = new Attachment();
			result.setAudit(a);

			return result;
		}

		public Collection<Attachment> findAll() {
			Collection<Attachment> result;
			result = attachmentRepository.findAll();

			return result;
		}

		public Attachment findOne(int attachmentId) {
			Attachment result;
			result = attachmentRepository.findOne(attachmentId);

			return result;
		}

		public Attachment save(Attachment attachment) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("AUDITOR");
			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Attachment result;
			result = attachmentRepository.save(attachment);

			return result;
		}


		public void delete(Attachment attachment) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("AUDITOR");
			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Assert.notNull(attachment);
			Assert.isTrue(attachment.getId() != 0);

			attachmentRepository.delete(attachment);
		}

	// Form methods --------------------------------
	
		public AttachmentForm generateForm(int auditId){
				AttachmentForm result;
				result = new AttachmentForm();
				result.setAuditId(auditId);
				
				return result;
				}
				
		public Attachment reconstruct(AttachmentForm attachmentForm,  BindingResult binding){
				Attachment result;
				Audit audit = auditService.findOne(attachmentForm.getAuditId());
				
				result = create(audit);
				result.setId(attachmentForm.getId());
				result.setUrl(attachmentForm.getUrl());
				
				validator.validate(result, binding);
				return result;				
				}
		
	}

