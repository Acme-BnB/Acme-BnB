
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;
import domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

	// Admin dashboard ------------------------------------------------

	@Query("select min(p.audits.size) from Property p")
	Double findMinAuditsProperties();

	@Query("select avg(p.audits.size) from Property p")
	Double findAvgAuditsProperties();

	@Query("select max(p.audits.size) from Property p")
	Double findMaxAuditsProperties();

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
