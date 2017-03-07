package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Commentable;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CommentableServiceTest extends AbstractTest{
	@Autowired
	private CommentableService commentableService;
	
	@Test
	public void testSave(){
		Commentable com=commentableService.findOne(20);
		com.setIsCommentable(false);
		commentableService.save(com);
		Assert.isTrue(com.getIsCommentable().equals(false));
	}
	
	@Test
	public void testfindCommentableById(){
		Commentable com=commentableService.findOne(20);
		Assert.notNull(com);
	}
	
}
