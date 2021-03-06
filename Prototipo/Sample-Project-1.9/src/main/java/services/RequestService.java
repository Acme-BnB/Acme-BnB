
package services;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import security.LoginService;
import security.UserAccount;
import domain.CreditCard;
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
		
		Assert.isTrue(check(request.getCreditCard()));

		result = requestRepository.save(request);

		return result;
	}

	public void delete(Request request) {

		Assert.notNull(request);
		Assert.isTrue(request.getId() != 0);

		requestRepository.delete(request);
	}
	
	public static boolean check(CreditCard creditCard){
		boolean validador = false;
        Calendar fecha = Calendar.getInstance();
        int mes = fecha.get(Calendar.MONTH)+1;
        int a�o = fecha.get(Calendar.YEAR);
        
        if(creditCard.getExpirationYear()>a�o){
        	validador=true;
        }else if(creditCard.getExpirationYear()==a�o){
        	if(creditCard.getExpirationYear()>=mes){
        		validador=true;
        	}
        }
        
        return validador;
	}

	public Collection<Request> findByCreator() {
		Collection<Request> result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = requestRepository.findByCreator(userAccount);

		return result;
	}

}
