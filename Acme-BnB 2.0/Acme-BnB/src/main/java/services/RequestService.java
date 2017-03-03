
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.RequestRepository;
import domain.CreditCard;
import domain.Property;
import domain.Request;
import domain.Tenant;
import forms.RequestForm;

@Service
@Transactional
public class RequestService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RequestRepository	requestRepository;


	// Supporting services ----------------------------------------------------

	@Autowired
	private TenantService tenantService;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private Validator validator;
	
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
	
	public static boolean check(CreditCard creditCard) {
		boolean validador = false;
		int sum = 0;
		Calendar fecha = Calendar.getInstance();
		String numero = creditCard.getNumber();
		int mes = fecha.get(Calendar.MONTH) + 1;
		int año = fecha.get(Calendar.YEAR);

		if (creditCard.getExpirationYear() > año) {
			validador = true;
		} else if (creditCard.getExpirationYear() == año) {
			if (creditCard.getExpirationMonth() >= mes) {
				validador = true;
			}
		}

		if (validador) {
			validador = false;
			for (int i = numero.length() - 1; i >= 0; i--) {
				int n = Integer.parseInt(numero.substring(i, i + 1));
				if (validador) {
					n *= 2;
					if (n > 9) {
						n = (n % 10) + 1;
					}
				}
				sum += n;
				validador = !validador;
			}
			if (sum % 10 == 0) {
				validador = true;
			}
		}

		return validador;
	}

	public Collection<Request> findByCreator(Tenant tenant) {
		Collection<Request> result;
		result = requestRepository.findByCreator(tenant);
		return result;
	}
	
	public Collection<Request> encryptCreditCard(Collection<Request> requests){
		Collection<Request> result = new ArrayList<Request>();
		CreditCard caux;
		String aux;
		
		for(Request r : requests){
			caux = r.getCreditCard();
			aux = "************"+caux.getNumber().substring(12);
			caux.setNumber(aux);
			r.setCreditCard(caux);
			result.add(r);
		}
	
		return result;
	}
	
	// Form methods --------------------------------
	
			public RequestForm generateForm(){
				RequestForm result;
				
				result = new RequestForm();
				
				return result;
			}
			
			public Request reconstruct(RequestForm requestForm,  BindingResult binding){
				Request result = create();
				
				Tenant tenant;
				Property property;
				
				tenant = tenantService.findByPrincipal();
				property = propertyService.findOne(requestForm.getPropertyId());
				
				//Assert.isTrue(check(requestForm.getCreditCard()));
				System.out.println(requestForm.getPropertyId());
				System.out.println(requestForm.getSmoker());
				result.setProperty(property);
				//result.setId(requestForm.getId());
				result.setTenant(tenant);
				result.setCheckIn(requestForm.getCheckIn());
				result.setCheckOut(requestForm.getCheckOut());
				result.setSmoker(requestForm.getSmoker());
				result.setStatus("PENDING");
				
			
				validator.validate(result, binding);
				
				return result;
			}
			/*
			public Request reconstruct(Request request, BindingResult binding){
				Request result;
				
				if(request.getId() == 0){
					Tenant tenant = tenantService.findByPrincipal();
					
					result = request;
					result.setTenant(tenant);
				}else{
					result = requestRepository.findOne(request.getId());
					
					result.setName(property.getName());
					result.setAddress(property.getAddress());
					
					Assert.isTrue(property.getRate()!=null, "nullRate");
					
					result.setRate(property.getRate());
					
					result.setRate(property.getRate());
					result.setDescription(property.getDescription());
					
					validator.validate(result, binding);
				}
				
				return result;
			}
			*/
			/*
			public RequestForm transform(Request request){
				RequestForm result=generateForm();
				result.setAddress(request.getAddress());
				result.setName(request.getName());
				result.setDescription(request.getDescription());
				result.setRate(request.getRate());
				return result;
			}
			*/

}
