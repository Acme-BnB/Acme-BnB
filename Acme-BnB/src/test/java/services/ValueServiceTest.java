package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import domain.Value;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
public class ValueServiceTest extends AbstractTest{

	//Service under test --------------------------
	@Autowired
	private ValueService valueService;
	
	@Autowired
	private AttributeService attributeService;
	
	@Autowired
	private PropertyService propertyService;
	
	//Tests ---------------------------------------
	//Positive ------------------------------------
	@Test
	public void testCreate(){
		Value value;
		super.authenticate("lessor1");
		value = valueService.create();
		super.authenticate(null);
		Assert.notNull(value);
	}
	
	@Test
	public void testSave(){
		Value value, saved;
		Collection<Value> values;
		super.authenticate("lessor1");
		value = valueService.create();
		value.setText("Texto");
		value.setAttribute(attributeService.findOne(32));
		value.setProperty(propertyService.findOne(37));
		saved = valueService.save(value);
		values = valueService.findAll();
		super.authenticate(null);
		Assert.isTrue(values.contains(saved));
	}

	@Test
	public void testDelete(){
		Value value, saved;
		Collection<Value> values;
		super.authenticate("lessor1");
		value = valueService.create();
		value.setAttribute(attributeService.findOne(32));
		value.setProperty(propertyService.findOne(37));
		value.setText("Texto");
		saved = valueService.save(value);
		valueService.delete(saved);
		values = valueService.findAll();
		super.authenticate(null);
		Assert.isTrue(!values.contains(saved));
	}
	
	@Test
	public void testFindOne(){
		Value value;
		super.authenticate("lessor1");
		value = valueService.findOne(41);
		super.authenticate(null);
		Assert.notNull(value);
	}
	
	@Test
	public void testFindAll(){
		Collection<Value> values;
		super.authenticate("lessor1");
		values = valueService.findAll();
		super.authenticate(null);
		Assert.notNull(values);
	}
	
}