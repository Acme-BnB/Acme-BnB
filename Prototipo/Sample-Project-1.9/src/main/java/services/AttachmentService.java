package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AttachmentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Attachment;
import domain.Audit;

@Service
@Transactional
public class AttachmentService {
	// Managed repository -----------------------------------------------------

		@Autowired
		private AttachmentRepository	attachmentRepository;


		// Supporting services ----------------------------------------------------

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
			Assert.notNull(result);

			return result;
		}

		public Attachment findOne(int attachmentId) {
			Attachment result;

			result = attachmentRepository.findOne(attachmentId);
			Assert.notNull(result);

			return result;
		}

		public Attachment save(Attachment attachment) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("AUDITOR");
			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Assert.notNull(attachment);

			Attachment result;

			result = attachmentRepository.save(attachment);

			return result;
		}

		public Attachment save2(Attachment attachment) {

			Assert.notNull(attachment);
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

	}

