
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import domain.Tenant;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TenantServiceTest extends AbstractTest {
	//Service under test --------------------------
		@Autowired
		private TenantService tenantService;

		
		//Tests ---------------------------------------
		//Positive
		@Test
		public void testCreate(){
			Tenant tenant = tenantService.create();
			Assert.notNull(tenant);
		}
		
		@Test
		public void testFindAll(){
			Collection<Tenant> all = tenantService.findAll();
			Assert.notNull(all);
		}
		
		@Test
		public void testFindOne(){
			Tenant tenant = tenantService.findOne(20);
			Assert.notNull(tenant);
		}

		@Test
		public void testSave(){
			int adminId = 20;
			String name = "Saturno";
			
			Tenant tenant = tenantService.findOne(adminId);
			tenant.setName(name);
			tenantService.save(tenant);
			
			Tenant tenantMod = tenantService.findOne(adminId);
			Assert.isTrue(tenantMod.getName().equals(name));
		}

		@Test
		public void testDelete(){
			// Before deleting the actor --------------------
			Tenant tenant = tenantService.findOne(22);
					
			tenantService.delete(tenant);
					
			// After deleting the actor ---------------------
			Collection<Tenant> all2 = tenantService.findAll();
			Assert.isTrue(!all2.contains(tenant));
		}

		
}
