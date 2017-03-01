
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.LessorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.CreditCard;
import domain.Lessor;
import domain.Property;
import domain.SocialIdentity;
import forms.LessorForm;

@Service
@Transactional
public class LessorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private LessorRepository	lessorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator			validator;


	// Constructors -----------------------------------------------------------

	public LessorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Lessor create() {

		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.LESSOR);
		authorities.add(a);
		userAccount.addAuthority(a);
		Lessor result = new Lessor();
		result.setUserAccount(userAccount);

		Collection<SocialIdentity> socialIdentities = new ArrayList<SocialIdentity>();
		Collection<Property> properties = new ArrayList<Property>();

		result.setIsCommentable(true);
		result.setSocialIdentities(socialIdentities);
		result.setRProperties(properties);

		return result;
	}

	public Collection<Lessor> findAll() {
		Collection<Lessor> result;

		result = lessorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Lessor findOne(int lessorId) {
		Lessor result;

		result = lessorRepository.findOne(lessorId);
		Assert.notNull(result);

		return result;
	}

	public Lessor save(Lessor lessor) {
		Assert.notNull(lessor);

		String password = lessor.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String md5 = encoder.encodePassword(password, null);
		lessor.getUserAccount().setPassword(md5);

		Assert.isTrue(check(lessor.getCreditCard()));

		Lessor result = lessorRepository.save(lessor);

		return result;
	}

	public Lessor save2(Lessor lessor) {
		Lessor result;

		result = lessorRepository.save(lessor);

		return result;
	}

	public void delete(Lessor lessor) {
		Assert.notNull(lessor);
		Assert.isTrue(lessor.getId() != 0);

		lessorRepository.delete(lessor);
	}

	// Form methods ------------------------------------------------

	public LessorForm generateForm() {
		LessorForm result;

		result = new LessorForm();
		return result;
	}

	public Lessor reconstruct(LessorForm lessorForm) {

		Lessor result = create();

		String password;
		password = lessorForm.getPassword();

		Assert.isTrue(lessorForm.getPassword2().equals(password), "notEqualPassword");
		Assert.isTrue(lessorForm.getAgreed(), "agreedNotAccepted");

		UserAccount userAccount;
		userAccount = new UserAccount();
		userAccount.setUsername(lessorForm.getUsername());
		userAccount.setPassword(password);

		Authority authority;
		authority = new Authority();
		authority.setAuthority(Authority.LESSOR);
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		result.setName(lessorForm.getName());
		result.setSurname(lessorForm.getSurname());
		result.setEmail(lessorForm.getEmail());
		result.setPhone(lessorForm.getPhone());
		result.setPicture(lessorForm.getPicture());
		result.setCreditCard(lessorForm.getCreditCard());
		result.setFeeAmount(0.0);
		return result;

	}

	public Lessor reconstruct(Lessor lessor, BindingResult binding) {
		Lessor result;

		if (lessor.getId() == 0) {
			result = lessor;
		} else {
			result = lessorRepository.findOne(lessor.getId());

			result.setName(lessor.getName());
			result.setSurname(lessor.getSurname());
			result.setEmail(lessor.getEmail());
			result.setPhone(lessor.getPhone());
			result.setPicture(lessor.getPicture());

			validator.validate(result, binding);
		}

		return result;
	}

	public Lessor findByUserAccount(UserAccount userAccount) {
		assert userAccount != null;

		Lessor result;

		result = lessorRepository.findByUserAccountId(userAccount.getId());
		assert result != null;

		return result;
	}

	// Other business services

	public Lessor findByPrincipal() {
		Lessor result;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();
		result = lessorRepository.findByUserAccountId(userAccountId);

		return result;
	}

	public Collection<Double> findAvgAcceptedAndDeniedPerLessor() {
		Collection<Double> result;
		Double aux;

		result = new ArrayList<Double>();

		aux = lessorRepository.findAvgAcceptedRequestPerLessor();
		result.add(aux);

		aux = lessorRepository.findAvgDeniedRequestPerLessor();
		result.add(aux);

		return result;
	}

	public Collection<Lessor> findLessorsMoreApprovedRequest() {
		Collection<Lessor> result;

		result = lessorRepository.findLessorsMoreApprovedRequest();

		return result;
	}

	public Collection<Lessor> findLessorsMoreDeniedRequest() {
		Collection<Lessor> result;

		result = lessorRepository.findLessorsMoreDeniedRequest();

		return result;
	}

	public Collection<Lessor> findLessorsMorePendingRequest() {
		Collection<Lessor> result;

		result = lessorRepository.findLessorsMorePendingRequest();

		return result;
	}

	public static boolean check(CreditCard creditCard) {
		boolean validador = false;
		Calendar fecha = Calendar.getInstance();
		int mes = fecha.get(Calendar.MONTH) + 1;
		int año = fecha.get(Calendar.YEAR);

		if (creditCard.getExpirationYear() > año) {
			validador = true;
		} else if (creditCard.getExpirationYear() == año) {
			if (creditCard.getExpirationYear() >= mes) {
				validador = true;
			}
		}

		return validador;
	}

}
