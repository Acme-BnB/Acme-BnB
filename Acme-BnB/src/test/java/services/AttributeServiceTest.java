
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Attribute;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AttributeServiceTest extends AbstractTest {

	// Service ----------------------------------------

	@Autowired
	private AttributeService	attributeService;


	//Tests ---------------------------------------
	@Test
	public void testCreate() {
		authenticate("admin");
		Attribute s;
		s = attributeService.create();
		Assert.notNull(s);
		authenticate(null);
	}

	@Test
	public void testFindAll() {
		Collection<Attribute> soc = attributeService.findAll();
		Assert.notEmpty(soc);
	}

	@Test
	public void testFindOne() {
		Attribute at = attributeService.findOne(30);
		Assert.notNull(at);
	}

	@Test
	public void testSave() {
		Attribute at = attributeService.findOne(30);
		Assert.notNull(at);
		at.setName("Manolo");
		attributeService.save2(at);
		Assert.isTrue(at.getName().equals("Manolo"));
	}

	@Test
	public void testDelete() {
		authenticate("admin");
		Attribute s;
		s = attributeService.create();
		Assert.notNull(s);
		Collection<Attribute> soc = attributeService.findAll();
		Assert.isTrue(!soc.contains(s));
		authenticate(null);
	}

}
