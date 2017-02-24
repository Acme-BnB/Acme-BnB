
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TenantRepository;
import security.Authority;
import security.UserAccount;
import domain.Request;
import domain.SocialIdentity;
import domain.Tenant;

@Service
@Transactional
public class TenantService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private TenantRepository	tenantRepository;


	// Supporting services ----------------------------------------------------

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

		Collection<SocialIdentity> socialIdentities = new ArrayList<SocialIdentity>();
		Collection<Request> request = new ArrayList<Request>();

		result.setSocialIdentities(socialIdentities);
		result.setRequests(request);

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

}
