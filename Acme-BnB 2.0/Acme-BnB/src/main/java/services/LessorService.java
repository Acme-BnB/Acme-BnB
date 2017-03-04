
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import domain.Comment;
import domain.CreditCard;
import domain.Lessor;
import domain.Property;
import domain.Request;
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
		Collection<Comment> comments = new ArrayList<Comment>();
		Collection<Comment> writtenComments = new ArrayList<Comment>();
		Collection<SocialIdentity> socialIdentities = new ArrayList<SocialIdentity>();
		Collection<Property> properties = new ArrayList<Property>();
		result.setComments(comments);
		result.setWrittenComments(writtenComments);
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

		//Assert.isTrue(check(lessor.getCreditCard()));

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

	public LessorForm generateForm(Lessor lessor) {
		LessorForm result;

		result = new LessorForm();

		result.setId(lessor.getId());
		result.setUsername(lessor.getUserAccount().getUsername());
		result.setPassword(lessor.getUserAccount().getPassword());
		result.setPassword2(lessor.getUserAccount().getPassword());
		result.setName(lessor.getName());
		result.setAgreed(true);
		result.setSurname(lessor.getSurname());
		result.setPhone(lessor.getPhone());
		result.setPicture(lessor.getPicture());
		result.setCreditCard(lessor.getCreditCard());
		result.setEmail(lessor.getEmail());

		return result;
	}

	public Lessor reconstruct(LessorForm lessorForm, BindingResult binding) {

		Lessor result = create();

		String password;
		password = lessorForm.getPassword();

		Assert.isTrue(lessorForm.getPassword2().equals(password), "notEqualPassword");
		Assert.isTrue(lessorForm.getAgreed(), "agreedNotAccepted");
		Assert.isTrue(check(lessorForm.getCreditCard()));

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

		validator.validate(result, binding);

		return result;

	}

	public Lessor reconstructEditPersonalData(LessorForm lessorForm, BindingResult binding) {
		Lessor result;

		result = lessorRepository.findOne(lessorForm.getId());

		result.setName(lessorForm.getName());
		result.setSurname(lessorForm.getSurname());
		result.setEmail(lessorForm.getEmail());
		result.setPhone(lessorForm.getPhone());
		result.setPicture(lessorForm.getPicture());
		result.setCreditCard(lessorForm.getCreditCard());

		validator.validate(result, binding);

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
		int sum = 0;
		Calendar fecha = Calendar.getInstance();
		String numero = creditCard.getNumber();
		int mes = fecha.get(Calendar.MONTH) + 1;
		int año = fecha.get(Calendar.YEAR);

		if (creditCard.getExpirationYear() > año) {
			validador = true;
		} else if (creditCard.getExpirationYear() == año) {
			if (creditCard.getExpirationMonth() >= mes) {
				validador = true;
			}
		}

		if (validador) {
			validador = false;
			for (int i = numero.length() - 1; i >= 0; i--) {
				int n = Integer.parseInt(numero.substring(i, i + 1));
				if (validador) {
					n *= 2;
					if (n > 9) {
						n = (n % 10) + 1;
					}
				}
				sum += n;
				validador = !validador;
			}
			if (sum % 10 == 0) {
				validador = true;
			}
		}

		return validador;
	}

	public Collection<Request> findRequestPerLessor(Lessor lessor) {
		Collection<Request> result;

		result = lessorRepository.findRequestPerLessor(lessor);

		return result;
	}

	public Map<Lessor, Double> map() {
		Map<Lessor, Double> map = new HashMap<Lessor, Double>();
		List<Object[]> aux = lessorRepository.maxMinRatio();
		for (Object[] o : aux) {
			map.put((Lessor) o[0], (Double) o[1]);
		}
		return map;
	}
	public Collection<Lessor> maxRatioLessor() {
		Collection<Lessor> result = new ArrayList<Lessor>();
		Map<Lessor, Double> maxMinRatio = map();
		Collection<Double> aux = maxMinRatio.values();
		Double max = Collections.max(aux);
		Collection<Lessor> lessors = maxMinRatio.keySet();
		for (Lessor l : lessors) {
			if (max == maxMinRatio.get(l)) {
				result.add(l);
			}
		}

		return result;
	}

	public Collection<Lessor> minRatioLessor() {
		Collection<Lessor> result = new ArrayList<Lessor>();
		Map<Lessor, Double> maxMinRatio = map();
		Collection<Double> aux = maxMinRatio.values();
		Double min = Collections.min(aux);
		Collection<Lessor> lessors = maxMinRatio.keySet();
		for (Lessor l : lessors) {
			if (min == maxMinRatio.get(l)) {
				result.add(l);
			}

		}
		return result;
	}

}
