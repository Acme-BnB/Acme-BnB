
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Finder;
import domain.Property;

@Service
@Transactional
public class FinderService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FinderRepository	finderRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public FinderService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Finder create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("TENANT");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Finder result;
		result = new Finder();

		Collection<Property> results = new ArrayList<Property>();

		result.setResults(results);

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

	// Others -----

	public Finder findByPrincipal() {
		Finder result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = finderRepository.findByUserAccount(userAccount);

		return result;
	}

}
