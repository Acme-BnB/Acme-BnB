
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import domain.Commentator;
import repositories.CommentatorRepository;
import security.LoginService;

@Service
@Transactional
public class CommentatorService {

	// Managed repository -----------------------------------------------------

	 @Autowired
	 private CommentatorRepository commentatorRepository;
	//
	 
	
	// Constructors -----------------------------------------------------------

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Commentator> findAll() {
		Collection<Commentator> result;
		result = commentatorRepository.findAll();

		return result;
	}

	public Commentator findOne(int commentatorId) {
		Commentator result;
		result = commentatorRepository.findOne(commentatorId);

		return result;
	}

	public Commentator save(Commentator commentator) {
		return commentatorRepository.save(commentator);
	}

	public void delete(Commentator commentator) {
		commentatorRepository.delete(commentator);
	}

	// Other bussines methods ---------------------------
	public Commentator findByPrincipal() {
		Commentator result;
		String userName;

		userName = LoginService.getPrincipal().getUsername();
		result = commentatorRepository.findByUserAccount(userName);
		return result;
	}
}
