
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

import repositories.TenantRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Finder;
import domain.Request;
import domain.SocialIdentity;
import domain.Tenant;
import forms.TenantForm;

@Service
@Transactional
public class TenantService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private TenantRepository	tenantRepository;

	@Autowired
	private FinderService		finderService;

	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator			validator;


	// Constructors -----------------------------------------------------------

	public TenantService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Tenant create() {

		UserAccount userAccount = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.TENANT);
		authorities.add(a);
		userAccount.addAuthority(a);
		Tenant result = new Tenant();
		result.setUserAccount(userAccount);
		Finder f;
		f = finderService.create();
		Finder f2 = finderService.save2(f);

		Collection<SocialIdentity> socialIdentities = new ArrayList<SocialIdentity>();
		Collection<Request> request = new ArrayList<Request>();
		Collection<Comment> writtenComments = new ArrayList<Comment>();
		Collection<Comment> comments = new ArrayList<Comment>();

		result.setFinder(f2);
		result.setIsCommentable(true);
		result.setSocialIdentities(socialIdentities);
		result.setRequests(request);
		result.setWrittenComments(writtenComments);
		result.setComments(comments);
		result.setIsCommentable(true);
		return result;
	}

	public Collection<Tenant> findAll() {
		Collection<Tenant> result;

		result = tenantRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Tenant findOne(int tenantId) {
		Tenant result;

		result = tenantRepository.findOne(tenantId);
		Assert.notNull(result);

		return result;
	}

	public Tenant save(Tenant tenant) {
		Assert.notNull(tenant);

		String password = tenant.getUserAccount().getPassword();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String md5 = encoder.encodePassword(password, null);
		tenant.getUserAccount().setPassword(md5);

		Tenant result = tenantRepository.save(tenant);

		return result;
	}

	public Tenant save2(Tenant tenant) {
		Tenant result;

		result = tenantRepository.save(tenant);

		return result;
	}

	public void delete(Tenant tenant) {
		Assert.notNull(tenant);
		Assert.isTrue(tenant.getId() != 0);

		tenantRepository.delete(tenant);
	}

	// Other business services ------------------------------------------

	public Tenant findByPrincipal() {
		Tenant result;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();
		result = tenantRepository.findByUserAccountId(userAccountId);

		return result;
	}

	public Double findAvgAcceptedRequestPerTenant() {
		Double result;

		result = tenantRepository.findAvgAcceptedRequestPerTenant();

		return result;
	}

	public Double findAvgDeniedRequestPerTenant() {
		Double result;

		result = tenantRepository.findAvgDeniedRequestPerTenant();

		return result;
	}

	public Collection<Tenant> findTenantMoreApprovedRequest() {
		Collection<Tenant> result;

		result = tenantRepository.findTenantMoreApprovedRequest();

		return result;
	}

	public Collection<Tenant> findTenantMoreDeniedRequest() {
		Collection<Tenant> result;

		result = tenantRepository.findTenantMoreDeniedRequest();

		return result;
	}

	public Collection<Tenant> findTenantMorePendingRequest() {
		Collection<Tenant> result;

		result = tenantRepository.findTenantMorePendingRequest();

		return result;
	}

	public Collection<Double> findMinAvgMaxNumberInvoiceToTheTenant() {
		Collection<Double> result;
		Double aux;

		result = new ArrayList<Double>();

		aux = tenantRepository.findMinNumberInvoiceToTheTenant();
		result.add(aux);

		aux = tenantRepository.findAvgNumberInvoiceToTheTenant();
		result.add(aux);

		aux = tenantRepository.findMaxNumberInvoiceToTheTenant();
		result.add(aux);

		return result;
	}
	public Collection<Double> findAvgAcceptedAndDeniedPerTenant() {

		Collection<Double> result = new ArrayList<Double>();

		Double a = tenantRepository.findAvgAcceptedRequestPerTenant();
		Double d = tenantRepository.findAvgDeniedRequestPerTenant();

		if (a == null || a == 0) {
			result.add(0.0);
		} else {
			result.add(a);
		}
		if (d == null || d == 0) {
			result.add(0.0);
		} else {
			result.add(d);
		}

		return result;
	}

	// Form methods -------------------------------------------------

	public TenantForm generateForm() {
		TenantForm result;

		result = new TenantForm();
		return result;
	}

	public TenantForm generateForm(Tenant tenant) {
		TenantForm result;

		result = new TenantForm();

		result.setId(tenant.getId());
		result.setUsername(tenant.getUserAccount().getUsername());
		result.setPassword(tenant.getUserAccount().getPassword());
		result.setPassword2(tenant.getUserAccount().getPassword());
		result.setName(tenant.getName());
		result.setAgreed(true);
		result.setSurname(tenant.getSurname());
		result.setPhone(tenant.getPhone());
		result.setPicture(tenant.getPicture());
		result.setEmail(tenant.getEmail());

		return result;
	}

	public Tenant reconstruct(TenantForm tenantForm, BindingResult binding) {

		Tenant result = create();

		String password;
		password = tenantForm.getPassword();

		Assert.isTrue(tenantForm.getPassword2().equals(password), "notEqualPassword");
		Assert.isTrue(tenantForm.getAgreed(), "agreedNotAccepted");

		UserAccount userAccount;
		userAccount = new UserAccount();
		userAccount.setUsername(tenantForm.getUsername());
		userAccount.setPassword(password);

		Authority authority;
		authority = new Authority();
		authority.setAuthority(Authority.TENANT);
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		result.setName(tenantForm.getName());
		result.setSurname(tenantForm.getSurname());
		result.setEmail(tenantForm.getEmail());
		result.setPhone(tenantForm.getPhone());
		result.setPicture(tenantForm.getPicture());

		validator.validate(result, binding);

		return result;
	}

	public Tenant reconstructEditPersonalData(TenantForm tenantForm, BindingResult binding) {
		Tenant result;

		result = tenantRepository.findOne(tenantForm.getId());

		result.setName(tenantForm.getName());
		result.setSurname(tenantForm.getSurname());
		result.setEmail(tenantForm.getEmail());
		result.setPhone(tenantForm.getPhone());
		result.setPicture(tenantForm.getPicture());

		validator.validate(result, binding);

		return result;
	}
}
