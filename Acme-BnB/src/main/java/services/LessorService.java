
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LessorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Lessor;
import domain.Property;
import domain.SocialIdentity;

@Service
@Transactional
public class LessorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private LessorRepository	lessorRepository;


	// Supporting services ----------------------------------------------------

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

	public Lessor findByPrincipal() {
		Lessor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		assert userAccount != null;
		result = findByUserAccount(userAccount);
		assert result != null;

		return result;
	}

	public Lessor findByUserAccount(UserAccount userAccount) {
		assert userAccount != null;

		Lessor result;

		result = lessorRepository.findByUserAccountId(userAccount.getId());
		assert result != null;

		return result;
	}

}
