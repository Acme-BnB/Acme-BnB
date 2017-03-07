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

import domain.Attachment;
import domain.Audit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AttachmentServiceTest extends AbstractTest{
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired 
	private AuditService auditService;
	
	
	//Tests ---------------------------------------
	@Test
	public void testCreate(){
		super.authenticate("auditor1");
		Audit a=auditService.findOne(59);
		Attachment at=attachmentService.create(a);
		Assert.notNull(at);
		super.authenticate(null);
		
	}
	@Test
	public void testFindAll(){
		Collection<Attachment>attachs=attachmentService.findAll();
		Assert.notNull(attachs);
	}
	@Test
	public void testFindOne(){
		Attachment at=attachmentService.findOne(55);
		Assert.notNull(at);
	}
	@Test
	public void testSave(){
		super.authenticate("auditor1");
		Audit a=auditService.findOne(57);
		Attachment at=attachmentService.create(a);
		attachmentService.save(at);
		Assert.notNull(at);
		super.authenticate(null);
	}
	@Test
	public void testDelete(){
		super.authenticate("auditor1");
		Attachment at=attachmentService.findOne(55);
		attachmentService.delete(at);
	}
	
	
}
