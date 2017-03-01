
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;
import domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

	@Query("select p from Property p where p.description like %?1% or p.address like %?1% or p.name like %?1%")
	Collection<Property> findByKey(String key);
	
	// Admin dashboard ------------------------------------------------

	@Query("select min(p.audits.size) from Property p")
	Double findMinAuditsPerProperty();
	
	@Query("select avg(p.audits.size) from Property p")
	Double findAvgAuditsPerProperty();
	
	@Query("select max(p.audits.size) from Property p")
	Double findMaxAuditsPerProperty();
	
	@Query("select p from Property p where p.lessor=?1 order by p.audits.size ")
	Collection<Property> findPropertiesOfALessorOrderByNumberAudit();

	@Query("select p from Property p where p.lessor=?1 order by p.requests.size")
	Collection<Property> findPropertiesOfALessorOrderByNumberRequest();

	@Query("select p from Property p join p.requests r where r.status='ACCEPTED' and p.lessor=?1 order by r")
	Collection<Property> findPropertiesOfALessorOrderByNumberRequestAccepted();

	@Query("select p from Property p join p.requests r where r.status='DENIED' and p.lessor=?1 order by r")
	Collection<Property> findPropertiesOfALessorOrderByNumberRequestDenied();

	@Query("select p from Property p join p.requests r where r.status='PENDING' and p.lessor=?1 order by r")
	Collection<Property> findPropertiesOfALessorOrderByNumberRequestPending();

	@Query("select 1.0*(select sum(p.requests.size) from Property p where p.audits.size>=1)/count(p) from Property p where p.audits.size>=1")
	Double findAvgRequestForPropertiesWithOneOrMoreAudit();

	@Query("select 1.0*(select sum(p.requests.size) from Property p where p.audits.size=0)/count(p) from Property p where p.audits.size=0")
	Double findAvgRequestForPropertiesWithZeroAudit();

	@Query("select p from Property p where p.lessor.userAccount = ?1")
	Collection<Property> findByUserAccount(UserAccount userAccount);

}
