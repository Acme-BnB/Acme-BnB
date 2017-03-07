package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Property;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
public class PropertyServiceTest extends AbstractTest{

	//Service under test --------------------------	
	@Autowired
	private LessorService lessorService;
	
	@Autowired
	private PropertyService propertyService;
	
	//Tests ---------------------------------------
	//Positive ------------------------------------
	@Test
	public void testCreate(){
		Property property;
		super.authenticate("lessor1");
		property = propertyService.create();
		super.authenticate(null);
		Assert.notNull(property);
	}
	
	@Test
	public void testSave(){
		Property property, saved;
		Collection<Property> properties;
		super.authenticate("lessor1");
		property = propertyService.create();
		property.setName("Property");
		property.setDescription("description");
		property.setRate(20.25);
		property.setAddress("Address");
		property.setLessor(lessorService.findOne(14));

		saved = propertyService.save(property);
		properties= propertyService.findAll();
		super.authenticate(null);
		Assert.isTrue(properties.contains(saved));
	}

	@Test
	public void testDelete(){
		Property property, saved;
		Collection<Property> properties;
		super.authenticate("lessor1");
		property = propertyService.create();
		property.setName("Property");
		property.setDescription("description");
		property.setRate(20.25);
		property.setAddress("Address");
		property.setLessor(lessorService.findOne(14));
		saved = propertyService.save(property);

		propertyService.delete(saved);
		properties = propertyService.findAll();
		super.authenticate(null);
		Assert.isTrue(!properties.contains(saved));
	}
	
	@Test
	public void testFindOne(){
		Property property;
		super.authenticate("lessor1");
		property = propertyService.findOne(37);
		super.authenticate(null);
		Assert.notNull(property);
	}
	
	@Test
	public void testFindAll(){
		Collection<Property> properties;
		super.authenticate("lessor1");
		properties = propertyService.findAll();
		super.authenticate(null);
		Assert.notNull(properties);
	}
	
}