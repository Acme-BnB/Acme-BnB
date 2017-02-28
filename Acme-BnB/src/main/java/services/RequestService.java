
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import security.LoginService;
import security.UserAccount;
import domain.Request;

@Service
@Transactional
public class RequestService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RequestRepository	requestRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public RequestService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Request create() {

		Request result;
		result = new Request();

		return result;
	}

	public Collection<Request> findAll() {
		Collection<Request> result;

		result = requestRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Request findOne(int requestId) {
		Request result;

		result = requestRepository.findOne(requestId);
		Assert.notNull(result);

		return result;
	}

	public Request save(Request request) {

		Assert.notNull(request);

		Request result;

		result = requestRepository.save(request);

		return result;
	}

	public void delete(Request request) {

		Assert.notNull(request);
		Assert.isTrue(request.getId() != 0);

		requestRepository.delete(request);
	}

	//others----

	public Collection<Request> findByPrincipal() {
		Collection<Request> result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = requestRepository.findByUserAccount(userAccount);

		return result;
	}

}
