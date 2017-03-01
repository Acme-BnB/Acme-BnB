
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Audit;

@Service
@Transactional
public class AuditService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AuditRepository	auditRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public AuditService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Audit create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("AUDITOR");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Audit result;
		result = new Audit();

		return result;
	}

	public Collection<Audit> findAll() {
		Collection<Audit> result;

		result = auditRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Audit findOne(int auditId) {
		Audit result;

		result = auditRepository.findOne(auditId);
		Assert.notNull(result);

		return result;
	}

	public Audit save(Audit audit) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("AUDITOR");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(audit);

		Audit result;

		result = auditRepository.save(audit);

		return result;
	}

	public Audit save2(Audit audit) {

		Assert.notNull(audit);
		Audit result;
		result = auditRepository.save(audit);

		return result;
	}

	public void delete(Audit audit) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("AUDITOR");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(audit);
		Assert.isTrue(audit.getId() != 0);

		auditRepository.delete(audit);
	}

}
