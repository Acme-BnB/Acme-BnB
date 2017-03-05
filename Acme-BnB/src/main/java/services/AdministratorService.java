
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.SocialIdentity;
import forms.AdministratorForm;

@Service
@Transactional
public class AdministratorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository	administratorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator				validator;


	// Constructors -----------------------------------------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Administrator create() {
		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		authorities.add(au);
		userAccount.setAuthorities(authorities);

		Administrator result;
		result = new Administrator();
		result.setUserAccount(userAccount);

		Collection<SocialIdentity> socialIdentities = new ArrayList<SocialIdentity>();
		result.setSocialIdentities(socialIdentities);

		return result;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> result;

		result = administratorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Administrator findOne(int administratorId) {
		Administrator result;

		result = administratorRepository.findOne(administratorId);
		Assert.notNull(result);

		return result;
	}

	public Administrator save(Administrator administrator) {
		Assert.notNull(administrator);

		Administrator result;

		result = administratorRepository.save(administrator);

		return result;
	}

	public void delete(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);

		administratorRepository.delete(administrator);
	}

	// Form methods ------------------------------------------------

	public AdministratorForm generateForm() {
		AdministratorForm result;

		result = new AdministratorForm();

		return result;
	}

	public AdministratorForm generateForm(Administrator administrator) {
		AdministratorForm result;

		result = new AdministratorForm();

		result.setId(administrator.getId());
		result.setUsername(administrator.getUserAccount().getUsername());
		result.setPassword(administrator.getUserAccount().getPassword());
		result.setPassword2(administrator.getUserAccount().getPassword());
		result.setName(administrator.getName());
		result.setAgreed(true);
		result.setSurname(administrator.getSurname());
		result.setPhone(administrator.getPhone());
		result.setPicture(administrator.getPicture());
		result.setEmail(administrator.getEmail());

		return result;
	}

	public void save2(Administrator administrator) {
		Md5PasswordEncoder encoder;
		String encodedPassword;

		Assert.notNull(administrator);

		encoder = new Md5PasswordEncoder();
		encodedPassword = encoder.encodePassword(administrator.getUserAccount().getPassword(), null);
		administrator.getUserAccount().setPassword(encodedPassword);

		administrator = save(administrator);
	}

	public Administrator reconstruct(AdministratorForm administratorForm) {
		Administrator result = create();

		String password;
		password = administratorForm.getPassword();

		Assert.isTrue(administratorForm.getPassword2().equals(password), "notEqualPassword");
		Assert.isTrue(administratorForm.getAgreed(), "agreedNotAccepted");

		UserAccount userAccount;
		userAccount = new UserAccount();
		userAccount.setUsername(administratorForm.getUsername());
		userAccount.setPassword(password);

		Authority authority;
		authority = new Authority();
		authority.setAuthority(Authority.ADMINISTRATOR);
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		result.setName(administratorForm.getName());
		result.setSurname(administratorForm.getSurname());
		result.setEmail(administratorForm.getEmail());
		result.setPhone(administratorForm.getPhone());
		result.setPicture(administratorForm.getPicture());

		return result;
	}

	public Administrator reconstructEditPersonalData(AdministratorForm administratorForm, BindingResult binding) {
		Administrator result;

		result = administratorRepository.findOne(administratorForm.getId());

		result.setName(administratorForm.getName());
		result.setSurname(administratorForm.getSurname());
		result.setEmail(administratorForm.getEmail());
		result.setPhone(administratorForm.getPhone());
		result.setPicture(administratorForm.getPicture());

		validator.validate(result, binding);

		return result;
	}

	// Other bussines methods -----------------------------------------------------

	public Administrator findByPrincipal() {
		Administrator result;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();
		result = administratorRepository.findByUserAccountId(userAccountId);

		return result;
	}

}
