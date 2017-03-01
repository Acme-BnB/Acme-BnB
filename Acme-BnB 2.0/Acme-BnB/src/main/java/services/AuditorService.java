
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Audit;
import domain.Auditor;
import domain.SocialIdentity;
import forms.AuditorForm;

@Service
@Transactional
public class AuditorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AuditorRepository	auditorRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public AuditorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Auditor create() {

		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		authorities.add(au);
		userAccount.setAuthorities(authorities);

		Auditor result;
		result = new Auditor();
		result.setUserAccount(userAccount);

		Collection<SocialIdentity> socialIdentities = new ArrayList<SocialIdentity>();
		Collection<Audit> audits = new ArrayList<Audit>();

		result.setSocialIdentities(socialIdentities);
		result.setAudits(audits);

		return result;
	}

	public Collection<Auditor> findAll() {
		Collection<Auditor> result;

		result = auditorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Auditor findOne(int auditorId) {
		Auditor result;

		result = auditorRepository.findOne(auditorId);
		Assert.notNull(result);

		return result;
	}

	public Auditor save(Auditor auditor) {
		Assert.notNull(auditor);

		String password = auditor.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String md5 = encoder.encodePassword(password, null);
		auditor.getUserAccount().setPassword(md5);

		Auditor result = auditorRepository.save(auditor);

		return result;
	}

	public Auditor save2(Auditor auditor) {
		Auditor result;

		result = auditorRepository.save(auditor);

		return result;
	}

	public void delete(Auditor auditor) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(auditor);
		Assert.isTrue(auditor.getId() != 0);

		auditorRepository.delete(auditor);
	}

	// Form methods -----------------------------------------------------

	public AuditorForm generateForm() {
		AuditorForm result;

		result = new AuditorForm();

		return result;
	}

	public Auditor reconstruct(AuditorForm auditorForm) {
		Auditor result = create();

		String password;
		password = auditorForm.getPassword();

		Assert.isTrue(auditorForm.getPassword2().equals(password), "notEqualPassword");
		Assert.isTrue(auditorForm.getAgreed(), "agreedNotAccepted");

		UserAccount userAccount;
		userAccount = new UserAccount();
		userAccount.setUsername(auditorForm.getUsername());
		userAccount.setPassword(password);

		Authority authority;
		authority = new Authority();
		authority.setAuthority(Authority.AUDITOR);
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		result.setName(auditorForm.getName());
		result.setSurname(auditorForm.getSurname());
		result.setEmail(auditorForm.getEmail());
		result.setPhone(auditorForm.getPhone());
		result.setCompanyName(auditorForm.getCompanyName());

		return result;
	}

}
