package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
public class CommentServiceTest extends AbstractTest{

	//Service under test --------------------------
	@Autowired
	private CommentService commentService;
	
	//Tests ---------------------------------------
	//Positive ------------------------------------
	@Test
	public void testCreate(){
		Comment comment;
		comment = commentService.create();
		Assert.notNull(comment);
	}
	
	@Test
	public void testSave(){
		Comment comment, saved, commentG;
		super.authenticate("admin");
		comment = commentService.findOne(67);
		comment.setTitle("titulo");
		saved = commentService.save(comment);
		commentG = commentService.findOne(67);
		Assert.isTrue(saved.equals(commentG));
	}

	@Test
	public void testFindOne(){
		Comment found;
		found = commentService.findOne(67);
		Assert.notNull(found);
	}
	
	
}