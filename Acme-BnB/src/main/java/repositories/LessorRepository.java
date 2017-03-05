
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Lessor;
import domain.Request;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Integer> {

	@Query("select l from Lessor l where l.name like %?1%")
	Collection<Lessor> findByKey(String key);

	@Query("select l from Lessor l where l.userAccount.id=?1")
	Lessor findByUserAccountId(int id);

	// Admin dashboard -----------------------------------------

	@Query("select 1.0*(select count(r1) from Request r1 where r1.status='ACCEPTED')/count(r) from Request r")
	Double findAvgAcceptedRequestPerLessor();

	@Query("select 1.0*(select count(r1) from Request r1 where r1.status='DENIED')/count(r) from Request r")
	Double findAvgDeniedRequestPerLessor();

	@Query("select r.property.lessor from Request r where r.status='ACCEPTED' group by r.property.lessor having count(r)>=all(select count(c) from Request c where c.status='ACCEPTED' group by c.property.lessor)")
	Collection<Lessor> findLessorsMoreApprovedRequest();

	@Query("select r.property.lessor from Request r where r.status='DENIED' group by r.property.lessor having count(r)>=all(select count(c) from Request c where c.status='DENIED' group by c.property.lessor)")
	Collection<Lessor> findLessorsMoreDeniedRequest();

	@Query("select r.property.lessor from Request r where r.status='PENDING' group by r.property.lessor having count(r)>=all(select count(c) from Request c where c.status='PENDING' group by c.property.lessor)")
	Collection<Lessor> findLessorsMorePendingRequest();

	@Query("select distinct r1.property.lessor, 1.0*(select count(r) from Request r where r.status='ACCEPTED' and r.property.lessor=r1.property.lessor)/count(r1) from Request r1 group by r1.property.lessor")
	List<Object[]> maxMinRatio();

	// Other ------------------------------------------------
	@Query("select r from Property p join p.requests r where p.lessor=?1")
	Collection<Request> findRequestPerLessor(Lessor lessor);

}
