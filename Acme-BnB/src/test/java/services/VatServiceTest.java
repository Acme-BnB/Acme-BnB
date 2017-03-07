
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
import domain.Vat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class VatServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------

	@Autowired
	private VatService	vatService;


	// Test ------------------------------------------------------------

	@Test
	public void testCreate() {
		Vat vat;
		super.authenticate("admin");
		vat = vatService.create();
		Assert.notNull(vat);
		super.authenticate(null);
	}

	@Test
	public void testFindAll() {
		Collection<Vat> vats;
		vats = vatService.findAll();
		Assert.notEmpty(vats);
	}

	@Test
	public void testSave() {
		Vat vat;
		super.authenticate("admin");
		vat = vatService.create();
		Assert.notNull(vat);
		vat.setValue("IT-78451578");
		Vat compare = vatService.save(vat);
		Assert.isTrue(compare.getValue().equals("IT-78451578"));
		super.authenticate(null);
	}

	@Test
	public void TestDelete() {
		Vat vat;
		super.authenticate("admin");
		vat = vatService.create();
		Assert.notNull(vat);
		vatService.save(vat);
		vatService.delete(vat);
		Assert.isNull(vat);
		super.authenticate(null);
	}

}
