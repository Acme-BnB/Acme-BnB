
package services;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PropertyRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Audit;
import domain.Finder;
import domain.Lessor;
import domain.Property;
import domain.Request;
import domain.Value;
import forms.PropertyForm;

@Service
@Transactional
public class PropertyService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PropertyRepository	propertyRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private LessorService		lessorService;

	@Autowired
	private Validator			validator;


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

		Lessor lessor = lessorService.findByPrincipal();

		Property result;
		result = new Property();

		Collection<Value> values = new ArrayList<Value>();
		Collection<Request> requests = new ArrayList<Request>();
		Collection<Audit> audits = new ArrayList<Audit>();

		result.setValues(values);
		result.setRequests(requests);
		result.setAudits(audits);
		result.setLessor(lessor);
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
	
	// Form methods --------------------------------
	
		public PropertyForm generateForm(){
			PropertyForm result;
			
			result = new PropertyForm();
			return result;
		}
		
		public Property reconstruct(PropertyForm propertyForm,  BindingResult binding){
			Property result = create();
			
			Lessor lessor;
			
			lessor = lessorService.findByPrincipal();
			
			result.setId(propertyForm.getId());
			result.setLessor(lessor);
			result.setName(propertyForm.getName());
			
			Assert.isTrue(propertyForm.getRate()==null, "nullRate");
			
			result.setRate(propertyForm.getRate());
			
			result.setDescription(propertyForm.getDescription());
			result.setAddress(propertyForm.getAddress());
		
			validator.validate(result, binding);
			
			return result;
		}
		
		public Property reconstruct(Property property, BindingResult binding){
			Property result;
			
			if(property.getId() == 0){
				Lessor lessor = lessorService.findByPrincipal();
				
				result = property;
				result.setLessor(lessor);
			}else{
				result = propertyRepository.findOne(property.getId());
				
				result.setName(property.getName());
				result.setAddress(property.getAddress());
				
				Assert.isTrue(property.getRate()!=null, "nullRate");
				
				result.setRate(property.getRate());
				
				result.setRate(property.getRate());
				result.setDescription(property.getDescription());
				
				validator.validate(result, binding);
			}
			
			return result;
		}
		
		public PropertyForm transform(Property property){
			PropertyForm result=generateForm();
			result.setAddress(property.getAddress());
			result.setName(property.getName());
			result.setDescription(property.getDescription());
			result.setRate(property.getRate());
			return result;
		}
		
	// Other business services

	public Collection<Double> findMinAvgMaxAuditsPerProperty() {
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

	public Collection<Property> findPropertiesOfALessorOrderByNumberAudit() {
		Collection<Property> result;

		result = propertyRepository.findPropertiesOfALessorOrderByNumberAudit();

		return result;
	}

	public Collection<Property> findPropertiesOfALessorOrderByNumberRequest() {
		Collection<Property> result;

		result = propertyRepository.findPropertiesOfALessorOrderByNumberRequest();

		return result;
	}

	public Collection<Property> findPropertiesOfALessorOrderByNumberRequestAccepted() {
		Collection<Property> result;

		result = propertyRepository.findPropertiesOfALessorOrderByNumberRequestAccepted();

		return result;
	}

	public Collection<Property> findPropertiesOfALessorOrderByNumberRequestDenied() {
		Collection<Property> result;

		result = propertyRepository.findPropertiesOfALessorOrderByNumberRequestDenied();

		return result;
	}

	public Collection<Property> findPropertiesOfALessorOrderByNumberRequestPending() {
		Collection<Property> result;

		result = propertyRepository.findPropertiesOfALessorOrderByNumberRequestPending();

		return result;
	}

	public Double findAvgRequestForPropertiesWithOneOrMoreAudit() {
		Double result;

		result = propertyRepository.findAvgRequestForPropertiesWithOneOrMoreAudit();

		return result;
	}

	public Double findAvgRequestForPropertiesWithZeroAudit() {
		Double result;

		result = propertyRepository.findAvgRequestForPropertiesWithZeroAudit();

		return result;
	}

	public Collection<Property> findByKey(String key,String destinationCity) {
		Collection<Property> result;

		result = propertyRepository.findByKey(key,destinationCity);

		return result;
	}
	public void findByFinder(Finder finder){
		Collection<Property> result=new ArrayList<Property>();
		Collection<Property> aux;
		if(finder.getKeyword()==null){
			aux=propertyRepository.findByDestination(finder.getDestinationCity());
		}else{
			aux=propertyRepository.findByKey(finder.getKeyword(), finder.getDestinationCity());
		}
		if(finder.getMinPrice()==null && finder.getMaxPrice()==null){
			result=aux;
		}else if(finder.getMinPrice()==null){
			for(Property p:aux){
				if(p.getRate()<=finder.getMaxPrice()){
					result.add(p);
				}
			}
		}else if(finder.getMaxPrice()==null){
			for(Property p:aux){
				if(p.getRate()>=finder.getMinPrice()){
					result.add(p);
				}
			}
		}else{
			for(Property p:aux){
				if(p.getRate()>=finder.getMinPrice() && p.getRate()<=finder.getMaxPrice()){
					result.add(p);
				}
			}
		}
		finder.setResults(result);
		
	}

	public Collection<Property> findByUserAccount() {
		Collection<Property> result;
		UserAccount userAccountId;

		userAccountId = LoginService.getPrincipal();
		result = propertyRepository.findByUserAccount(userAccountId);

		return result;
	}
}
