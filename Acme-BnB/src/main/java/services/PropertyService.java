
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PropertyRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Audit;
import domain.Property;
import domain.Request;
import domain.Value;

@Service
@Transactional
public class PropertyService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PropertyRepository	propertyRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public PropertyService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Property create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("LESSOR");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Property result;
		result = new Property();

		Collection<Value> values = new ArrayList<Value>();
		Collection<Request> requests = new ArrayList<Request>();
		Collection<Audit> audits = new ArrayList<Audit>();

		result.setValues(values);
		result.setRequests(requests);
		result.setAudits(audits);

		return result;
	}

	public Collection<Property> findAll() {
		Collection<Property> result;

		result = propertyRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Property findOne(int propertyId) {
		Property result;

		result = propertyRepository.findOne(propertyId);
		Assert.notNull(result);

		return result;
	}

	public Property save(Property property) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("LESSOR");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(property);

		Property result;

		result = propertyRepository.save(property);

		return result;
	}

	public Property save2(Property property) {

		Assert.notNull(property);
		Property result;
		result = propertyRepository.save(property);

		return result;
	}

	public void delete(Property property) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("LESSOR");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(property);
		Assert.isTrue(property.getId() != 0);

		propertyRepository.delete(property);
	}
}
