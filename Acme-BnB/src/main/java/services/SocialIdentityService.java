
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SocialIdentityRepository;
import domain.SocialIdentity;

@Service
@Transactional
public class SocialIdentityService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SocialIdentityRepository	socialIdentityRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public SocialIdentityService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public SocialIdentity create() {

		SocialIdentity result;
		result = new SocialIdentity();

		return result;
	}

	public Collection<SocialIdentity> findAll() {
		Collection<SocialIdentity> result;

		result = socialIdentityRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public SocialIdentity findOne(int socialIdentityId) {
		SocialIdentity result;

		result = socialIdentityRepository.findOne(socialIdentityId);
		Assert.notNull(result);

		return result;
	}

	public SocialIdentity save(SocialIdentity socialIdentity) {

		Assert.notNull(socialIdentity);

		SocialIdentity result;

		result = socialIdentityRepository.save(socialIdentity);

		return result;
	}

	public void delete(SocialIdentity socialIdentity) {

		Assert.notNull(socialIdentity);
		Assert.isTrue(socialIdentity.getId() != 0);

		socialIdentityRepository.delete(socialIdentity);
	}

}
