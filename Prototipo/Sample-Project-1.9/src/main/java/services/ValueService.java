
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ValueRepository;
import domain.Value;

@Service
@Transactional
public class ValueService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ValueRepository	valueRepository;


	// Supporting services ----------------------------------------------------

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
}
