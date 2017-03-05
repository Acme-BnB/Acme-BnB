
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ValueRepository;
import domain.Property;
import domain.Value;
import forms.ValueForm;

@Service
@Transactional
public class ValueService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ValueRepository	valueRepository;


	// Supporting services ----------------------------------------------------

	@Autowired
	private Validator validator;
	
	@Autowired
	private PropertyService propertyService;
	
	// Constructors -----------------------------------------------------------

	public ValueService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Value create() {

		Value result;
		result = new Value();

		return result;
	}

	public Collection<Value> findAll() {
		Collection<Value> result;

		result = valueRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Value findOne(int valueId) {
		Value result;

		result = valueRepository.findOne(valueId);
		Assert.notNull(result);

		return result;
	}

	public Value save(Value value) {

		Assert.notNull(value);

		Value result;

		result = valueRepository.save(value);

		return result;
	}

	public void delete(Value value) {

		Assert.notNull(value);
		Assert.isTrue(value.getId() != 0);

		valueRepository.delete(value);
	}
	
	// Form methods ------------------------------------------------

		public ValueForm generateForm() {
			ValueForm result;

			result = new ValueForm();
			return result;
		}

		public ValueForm generateForm(Value value) {
			ValueForm result;

			result = new ValueForm();

			result.setAttribute(value.getAttribute());
			result.setText(value.getText());
			result.setPropertyId(value.getProperty().getId());

			return result;
		}

		public Value reconstruct(ValueForm valueForm, BindingResult binding) {

			Value result = create();
			
			Property property;
			
			property = propertyService.findOne(valueForm.getPropertyId());

			result.setAttribute(valueForm.getAttribute());
			result.setText(valueForm.getText());
			result.setProperty(property);

			validator.validate(result, binding);

			return result;

		}


}
