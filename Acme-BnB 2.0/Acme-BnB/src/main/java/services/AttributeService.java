
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AttributeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Attribute;
import domain.Value;
import forms.AttributeForm;

@Service
@Transactional
public class AttributeService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AttributeRepository	attributeRepository;
	
	@Autowired
	private Validator	validator;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public AttributeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Attribute create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Attribute result;
		result = new Attribute();

		Collection<Value> values = new ArrayList<Value>();

		result.setValues(values);

		return result;
	}

	public Collection<Attribute> findAll() {
		Collection<Attribute> result;

		result = attributeRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Attribute findOne(int attributeId) {
		Attribute result;

		result = attributeRepository.findOne(attributeId);
		Assert.notNull(result);

		return result;
	}

	public Attribute save(Attribute attribute) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(attribute);

		Attribute result;

		result = attributeRepository.save(attribute);

		return result;
	}

	public Attribute save2(Attribute attribute) {

		Assert.notNull(attribute);
		Attribute result;
		result = attributeRepository.save(attribute);

		return result;
	}

	public void delete(Attribute attribute) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(attribute);
		Assert.isTrue(attribute.getId() != 0);

		attributeRepository.delete(attribute);
	}
	public AttributeForm generateForm(){
		AttributeForm result;
		
		result = new AttributeForm();
		return result;
	}
	
	public Attribute reconstruct(AttributeForm attributeForm,  BindingResult binding){
		Attribute result = create();
		
		
		result.setId(attributeForm.getId());
		result.setName(attributeForm.getName());
		validator.validate(result, binding);
		
		return result;
	}
	
	public Attribute reconstruct(Attribute attribute, BindingResult binding){
		Attribute result;
		
		if(attribute.getId() == 0){
			result = attribute;
			
		}else{
			result = attributeRepository.findOne(attribute.getId());
			
			result.setName(attribute.getName());
			
			
			validator.validate(result, binding);
		}
		
		return result;
	}
	
	public AttributeForm transform(Attribute attribute){
		AttributeForm result=generateForm();
		result.setName(attribute.getName());
		return result;
	}

}
