
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;

import security.Authority;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest {
	//Service under test --------------------------
		@Autowired
		private AdministratorService administratorService;

		
		//Tests ---------------------------------------
		//Positive
		@Test
		public void testCreate(){
			Administrator admin = administratorService.create();
			Assert.notNull(admin);
		}
		
		@Test
		public void testFindAll(){
			Collection<Administrator> all = administratorService.findAll();
			Assert.notNull(all);
			Assert.isTrue(all.size() == 1);
		}
		
		@Test
		public void testFindOne(){
			Administrator admin = administratorService.findOne(13);
			Authority au = new Authority();
			au.setAuthority("ADMIN");
			Assert.notNull(admin);
			Assert.isTrue(admin.getUserAccount().getAuthorities().contains(au));
		}

		@Test
		public void testSave(){
			int adminId = 13;
			String name = "Saturno";
			
			Administrator admin = administratorService.findOne(adminId);
			Assert.notNull(admin);
			admin.setName(name);
			administratorService.save(admin);
			
			Administrator adminMod = administratorService.findOne(adminId);
			Assert.isTrue(adminMod.getName().equals(name));
		}

		@Test
		public void testDelete(){
			// Before deleting the actor --------------------
			Administrator admin = administratorService.findOne(13);
			Collection<Administrator> all1 = administratorService.findAll();
			Assert.isTrue(all1.contains(admin));
					
			administratorService.delete(admin);
					
			// After deleting the actor ---------------------
			Collection<Administrator> all2 = administratorService.findAll();
			Assert.isTrue(!all2.contains(admin));
		}

		@Test
		public void testFindByPrincipal(){
			super.authenticate("admin");
			
			Administrator principal = administratorService.findOne(18);
			
			Administrator admin = administratorService.findByPrincipal();
			Assert.isTrue(principal.equals(admin));
			
			super.authenticate(null);
		}
		
}
