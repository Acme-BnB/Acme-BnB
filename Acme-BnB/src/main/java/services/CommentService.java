
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Comment;
import forms.CommentForm;

import repositories.CommentRepository;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------

	 @Autowired
	 private CommentRepository commentRepository;
	 
	// Supporting services ----------------------------------------------------
	 
	 @Autowired
	 private CommentatorService	 commentatorService;
	 
	 @Autowired
	 private CommentableService	 commentableService;
	 
	 
	 @Autowired
	 private Validator	 validator;
	 
	
	// Constructors -----------------------------------------------------------

	public CommentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Comment create() {
		Date date = new Date(System.currentTimeMillis() - 1000);
		Comment result;

		result = new Comment();
		result.setPostedMoment(date);

		return result;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;
		result = commentRepository.findAll();
		return result;
	}

	public Comment findOne(int commentId) {
		Comment result;
		result = commentRepository.findOne(commentId);
		return result;
	}

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	public void delete(Comment comment) {
		commentRepository.delete(comment);
	}

	// Other bussines methods ---------------------------
	public CommentForm generateForm(int commentableId) {
		CommentForm commentForm = new CommentForm();

		commentForm.setCommentableId(commentableId);
		return commentForm;
	}

	public Comment reconstruct(CommentForm commentForm, BindingResult binding) {
		Comment result = create();

		Assert.notNull(commentatorService.findByPrincipal(), "notCommentator");
		result.setCommentator(commentatorService.findByPrincipal());
		Assert.notNull(commentableService.findCommentableById(commentForm.getCommentableId()), "notCommentable");
		result.setCommentable(commentableService.findOne(commentForm.getCommentableId()));
		result.setStars(commentForm.getStars());
		result.setText(commentForm.getText());
		result.setTitle(commentForm.getTitle());
		
		validator.validate(result, binding);

		return result;
	}

}
