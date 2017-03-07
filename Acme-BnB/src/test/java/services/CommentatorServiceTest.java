package services;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Commentator;

import utilities.AbstractTest;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CommentatorServiceTest extends AbstractTest{
	@Autowired
	private CommentatorService commentatorService;

	
	@Test 
	public void testFindAll(){
		Collection<Commentator>com=commentatorService.findAll();
		Assert.notNull(com);
	}
	@Test
	public void testFindOne(){
		Commentator com=commentatorService.findOne(20);
		Assert.notNull(com);
	}
}
	
	
