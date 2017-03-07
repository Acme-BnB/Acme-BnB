
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
import domain.Finder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FinderServiceTest extends AbstractTest {

	// Service under test -----------------------------------

	@Autowired
	private FinderService	finderService;


	// Test --------------------------------------------------

	@Test
	public void testCreate() {
		Finder f = finderService.create();
		Assert.notNull(f);
	}

	@Test
	public void testFindOne() {
		Finder f = finderService.findOne(18);
		Assert.notNull(f);
	}

	@Test
	public void testFindAll() {
		Collection<Finder> finds;
		finds = finderService.findAll();
		Assert.notEmpty(finds);
	}

	@Test
	public void testSave() {
		super.authenticate("tenant1");
		Finder f = finderService.findOne(18);
		Assert.notNull(f);
		f.setDestinationCity("Malaga");
		finderService.save(f);
		Assert.isTrue(f.getDestinationCity().equals("Malaga"));
		authenticate(null);
	}

}
