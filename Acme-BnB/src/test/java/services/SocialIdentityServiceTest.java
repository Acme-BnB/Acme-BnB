package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.SocialIdentity;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
public class SocialIdentityServiceTest extends AbstractTest{

	//Service under test --------------------------
	@Autowired
	private SocialIdentityService socialIdentityService;
	
	//Tests ---------------------------------------
	@Test
	public void testCreate(){
		authenticate("admin");
		SocialIdentity s;
		s=socialIdentityService.create();
		Assert.notNull(s);
		authenticate(null);
	}
	
	@Test
	public void testSave(){
		SocialIdentity s;
		s=socialIdentityService.findOne(26);
		s.setNick("pakitoconk");
		socialIdentityService.save(s);
		Assert.isTrue(s.getNick().equals("pakitoconk"));
		
	}
	
	@Test
	public void testFindOne(){
		SocialIdentity s;
		s=socialIdentityService.findOne(26);
		Assert.notNull(s);
	
	}
}