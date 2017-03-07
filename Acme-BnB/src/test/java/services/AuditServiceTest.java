
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
import domain.Audit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AuditServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------

	@Autowired
	private AuditService	auditService;


	// Test ------------------------------------------------------------

	@Test
	public void testCreate() {
		Audit audit;
		super.authenticate("auditor1");
		audit = auditService.create();
		Assert.notNull(audit);
		super.authenticate(null);
	}

	@Test
	public void testFindAll() {
		Collection<Audit> audits;
		audits = auditService.findAll();
		Assert.notEmpty(audits);
	}

	@Test
	public void testFindOne() {
		Audit audit = auditService.findOne(51);
		Assert.notNull(audit);
	}

	@Test
	public void testSave() {
		Audit audit;
		super.authenticate("auditor1");
		audit = auditService.findOne(51);
		Assert.notNull(audit);
		audit.setText("Esto es un test de save");
		auditService.save(audit);
		Assert.isTrue(audit.getText().equals("Esto es un test de save"));
	}

}
