package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Auditor;

import utilities.AbstractTest;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AuditorServiceTest extends AbstractTest{
	@Autowired
	private AuditorService auditorService;
	
	@Test
	public void testCreate(){
		super.authenticate("admin");
		Auditor a=auditorService.create();
		Assert.notNull(a);
		super.authenticate(null);
	}
	@Test
	public void testFindAll(){
		Collection<Auditor>auditors=auditorService.findAll();
		Assert.notNull(auditors);
	}
	@Test
	public void testFindOne(){
		Auditor a=auditorService.findOne(23);
		Assert.notNull(a);
	}
	@Test
	public void testSave(){
		Auditor a=auditorService.findOne(23);
		String cad="NombreCambiado";
		a.setName(cad);
		auditorService.save2(a);
		Assert.isTrue(a.getName().equals(cad));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testDelete(){
		Auditor a=auditorService.findOne(23);
		auditorService.delete(a);
		Assert.notNull(auditorService.findOne(23));
	}
}
