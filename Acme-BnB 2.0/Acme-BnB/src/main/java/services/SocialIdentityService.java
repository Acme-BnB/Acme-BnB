
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SocialIdentityRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.SocialIdentity;
import forms.SocialIdentityForm;

@Service
@Transactional
public class SocialIdentityService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SocialIdentityRepository	socialIdentityRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator					validator;

	@Autowired
	private ActorService				actorService;


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

	// Other business services

	public Collection<SocialIdentity> findByPrincipal() {
		Collection<SocialIdentity> result;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();
		result = socialIdentityRepository.findByUserAccountId(userAccountId);

		return result;
	}

	public Collection<SocialIdentity> findByPrincipal2() {
		Collection<SocialIdentity> result;
		Actor actor;
		actor = actorService.findByPrincipal();

		result = socialIdentityRepository.findByUserActor(actor);

		return result;
	}

	public Collection<SocialIdentity> findByUserAccount() {
		Collection<SocialIdentity> result;
		UserAccount userAccountId;

		userAccountId = LoginService.getPrincipal();
		result = socialIdentityRepository.findByUserAccount(userAccountId);

		return result;
	}

	// Form methods --------------------------------

	public SocialIdentityForm generateForm() {
		SocialIdentityForm result;

		result = new SocialIdentityForm();
		return result;
	}

	public SocialIdentity reconstruct(SocialIdentityForm socialIdentityForm, BindingResult binding) {
		SocialIdentity result = create();

		Actor actor;

		actor = actorService.findByPrincipal();

		result.setId(socialIdentityForm.getId());
		result.setActor(actor);

		result.setNick(socialIdentityForm.getNick());
		result.setSocialNetwork(socialIdentityForm.getSocialNetwork());
		result.setProfileURL(socialIdentityForm.getProfileURL());

		validator.validate(result, binding);

		return result;
	}

	public SocialIdentity reconstruct(SocialIdentity socialIdentity, BindingResult binding) {
		SocialIdentity result;

		if (socialIdentity.getId() == 0) {
			Actor actor = actorService.findByPrincipal();

			result = socialIdentity;
			result.setActor(actor);
		} else {
			result = socialIdentityRepository.findOne(socialIdentity.getId());

			result.setNick(socialIdentity.getNick());
			result.setSocialNetwork(socialIdentity.getSocialNetwork());
			result.setProfileURL(socialIdentity.getProfileURL());

			validator.validate(result, binding);
		}

		return result;
	}

	public SocialIdentityForm transform(SocialIdentity socialIdentity) {
		SocialIdentityForm result = generateForm();
		result.setNick(socialIdentity.getNick());
		result.setSocialNetwork(socialIdentity.getSocialNetwork());
		result.setProfileURL(socialIdentity.getProfileURL());
		return result;
	}

}
