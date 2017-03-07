package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.Request;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RequestServiceTest extends AbstractTest{
	
	@Autowired
	private RequestService requestService;
	
	@Test
	public void testCreate(){
		super.authenticate("tenant1");
		Request r=requestService.create();
		Assert.notNull(r);
		super.authenticate(null);
	}
	@Test
	public void testSave(){
		Request r=requestService.findOne(50);
		r.setSmoker(true);
		requestService.save(r);
		Assert.isTrue(r.getSmoker());
		
	}
	
	@Test
	public void testFindAll(){
		Collection<Request>req=requestService.findAll();
		Assert.notNull(req);
	}
	@Test
	public void testFindOne(){
		Request r=requestService.findOne(50);
		Assert.notNull(r);
	}
	@Test
	public void testCheck(){
		CreditCard c=new CreditCard();
		c.setBrandName("VISA");
		c.setHolderName("Jose");
		c.setNumber("4759292866488602");
		c.setExpirationMonth(11);
		c.setExpirationYear(2019);
		c.setCvv(124);
		Assert.isTrue(requestService.check(c));
	}
}
