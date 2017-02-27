
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
	
	// Other business services
	
	public Collection<Double> findMinAvgMaxAuditsPerProperty(){
		Collection<Double> result;
		Double aux;
		
		result = new ArrayList<Double>();
		
		aux = propertyRepository.findMinAuditsPerProperty();
		result.add(aux);
		
		aux = propertyRepository.findAvgAuditsPerProperty();
		result.add(aux);
		
		aux = propertyRepository.findMaxAuditsPerProperty();
		result.add(aux);
		
		return result;
	}
	
	public Collection<Property> findPropertiesOfALessorOrderByNumberAudit(){
		Collection<Property> result;
		
		result = propertyRepository.findPropertiesOfALessorOrderByNumberAudit();
		
		return result;
	}
	
	public Collection<Property> findPropertiesOfALessorOrderByNumberRequest(){
		Collection<Property> result;
		
		result = propertyRepository.findPropertiesOfALessorOrderByNumberRequest();
		
		return result;
	}
	
	public Collection<Property> findPropertiesOfALessorOrderByNumberRequestAccepted(){
		Collection<Property> result;
		
		result = propertyRepository.findPropertiesOfALessorOrderByNumberRequestAccepted();
		
		return result;
	}
	
	public Collection<Property> findPropertiesOfALessorOrderByNumberRequestDenied(){
		Collection<Property> result;
		
		result = propertyRepository.findPropertiesOfALessorOrderByNumberRequestDenied();
		
		return result;
	}
	
	public Collection<Property> findPropertiesOfALessorOrderByNumberRequestPending(){
		Collection<Property> result;
		
		result = propertyRepository.findPropertiesOfALessorOrderByNumberRequestPending();
		
		return result;
	}
	
	public Double findAvgRequestForPropertiesWithOneOrMoreAudit(){
		Double result;
		
		result = propertyRepository.findAvgRequestForPropertiesWithOneOrMoreAudit();
		
		return result;
	}
	
	public Double findAvgRequestForPropertiesWithZeroAudit(){
		Double result;
		
		result = propertyRepository.findAvgRequestForPropertiesWithZeroAudit();
		
		return result;
	}
	
	public Collection<Property> findByKey(String key){
		Collection<Property> result;
		
		result = propertyRepository.findByKey(key);
		
		return result;
	}
}
