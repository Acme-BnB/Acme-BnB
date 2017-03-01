package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import repositories.VatRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Vat;

@Service
@Transactional
public class VatService {
	// Managed repository -----------------------------------------------------

		@Autowired
		private VatRepository	vatRepository;


		// Supporting services ----------------------------------------------------

		// Constructors -----------------------------------------------------------

		public VatService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------

		public Vat create() {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");
			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Vat result;

			result = new Vat();

			return result;
		}

		public Collection<Vat> findAll() {
			Collection<Vat> result;

			result = vatRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public Vat findOne(int vatId) {
			Assert.isTrue(vatId != 0);

			Vat result;

			result = vatRepository.findOne(vatId);
			Assert.notNull(result);

			return result;
		}

		public Vat save(Vat vat) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");
			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Assert.notNull(vat);

			return vatRepository.save(vat);
		}

		public void delete(Vat vat) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			Authority au = new Authority();
			au.setAuthority("ADMIN");
			Assert.isTrue(userAccount.getAuthorities().contains(au));

			Assert.notNull(vat);
			Assert.isTrue(vat.getId() != 0);
			Assert.isTrue(vatRepository.exists(vat.getId()));

			vatRepository.delete(vat);
		}


}
