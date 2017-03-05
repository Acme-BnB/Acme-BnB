
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Finder;
import domain.Tenant;

import domain.Property;
import forms.FinderForm;


@Service
@Transactional
public class FinderService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FinderRepository	finderRepository;
	
	@Autowired
	private TenantService	tenantService;
	
	@Autowired
	private Validator			validator;



	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public FinderService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Finder create() {

		Finder result;
		result = new Finder();
		Date date = new Date(System.currentTimeMillis() - 1);

		Collection<Property> results = new ArrayList<Property>();

		result.setResults(results);
		result.setDestinationCity("Ciudad");
		result.setMinPrice(0.0);
		result.setLastTimeSearched(date);

		save2(result);

		return result;
	}

	public Collection<Finder> findAll() {
		Collection<Finder> result;

		result = finderRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Finder findOne(int finderId) {
		Finder result;

		result = finderRepository.findOne(finderId);
		Assert.notNull(result);

		return result;
	}

	public Finder save(Finder finder) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("TENANT");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(finder);

		Finder result;

		result = finderRepository.save(finder);

		return result;
	}

	public Finder save2(Finder finder) {

		Assert.notNull(finder);
		Finder result;
		result = finderRepository.save(finder);

		return result;
	}

	public void delete(Finder finder) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("TENANT");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(finder);
		Assert.isTrue(finder.getId() != 0);

		finderRepository.delete(finder);
	}

	// Other business services 

	public Collection<Double> findMinAvgMaxResultPerFinder() {
		Collection<Double> result;
		Double aux;

		result = new ArrayList<Double>();

		aux = finderRepository.findMinResultPerFinder();
		result.add(aux);

		aux = finderRepository.findAvgResultPerFinder();
		result.add(aux);

		aux = finderRepository.findMaxResultPerFinder();
		result.add(aux);

		return result;
	}

	// Others -----

	public Finder findByPrincipal() {
		Finder result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = finderRepository.findByUserAccount(userAccount);

		return result;
	}
	public FinderForm generateForm() {
		FinderForm result;

		result = new FinderForm();
		return result;
	}
	
	public Finder reconstruct(FinderForm finderForm, BindingResult binding) {
		Tenant tenant=tenantService.findByPrincipal();
		
		Finder result = tenant.getFinder();
		result.setDestinationCity(finderForm.getDestinationCity());
		result.setMinPrice(finderForm.getMinPrice());
		result.setMaxPrice(finderForm.getMaxPrice());
		result.setKeyword(finderForm.getKeyword());
		Date d=new Date(System.currentTimeMillis()-10000);
		result.setLastTimeSearched(d);
		validator.validate(result, binding);
		
		return result;
	}

	public FinderForm transform(Finder finder){
		FinderForm result=generateForm();
		result.setDestinationCity(finder.getDestinationCity());
		result.setMinPrice(finder.getMinPrice());
		result.setMaxPrice(finder.getMaxPrice());
		result.setKeyword(finder.getKeyword());
		return result;
	}

	public Boolean compareSearch(Finder finder){
		Finder old=findByPrincipal();
		Boolean res=false;
		if(old.getDestinationCity().compareTo(finder.getDestinationCity())!=0 && old.getMaxPrice().compareTo(finder.getMaxPrice())!=0 
				&& old.getMinPrice().compareTo(finder.getMinPrice())!=0 && old.getKeyword().compareTo(finder.getKeyword())!=0){
			res=true;
		}
		return res;
	}
	public Collection<Double> findAvgMinMaxResultPerFinder() {
		Collection<Double> result;
		Double aux;

		result = new ArrayList<Double>();

		aux = finderRepository.findAvgResultPerFinder();
		result.add(aux);

		aux = finderRepository.findMinResultPerFinder();
		result.add(aux);

		aux = finderRepository.findMaxResultPerFinder();
		result.add(aux);

		return result;
	}

}
