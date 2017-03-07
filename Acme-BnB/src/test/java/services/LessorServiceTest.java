
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import domain.Lessor;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class LessorServiceTest extends AbstractTest {
	//Service under test --------------------------
		@Autowired
		private LessorService lessorService;

		
		//Tests ---------------------------------------
		//Positive
		@Test
		public void testCreate(){
			Lessor lessor = lessorService.create();
			Assert.notNull(lessor);
		}
		
		@Test
		public void testFindAll(){
			Collection<Lessor> all = lessorService.findAll();
			Assert.notNull(all);
		}
		
		@Test
		public void testFindOne(){
			Lessor lessor = lessorService.findOne(16);
			Assert.notNull(lessor);
		}

		@Test
		public void testSave(){
			int adminId = 16;
			String name = "Saturno";
			
			Lessor lessor = lessorService.findOne(adminId);
			lessor.setName(name);
			lessorService.save(lessor);
			
			Lessor lessorMod = lessorService.findOne(adminId);
			Assert.isTrue(lessorMod.getName().equals(name));
		}
		
}
