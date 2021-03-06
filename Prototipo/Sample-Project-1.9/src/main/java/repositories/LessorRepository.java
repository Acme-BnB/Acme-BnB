package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Lessor;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Integer>{
	
	@Query("select l from Lessor l where l.name like %?1%")
	Collection<Lessor> findByKey(String key);
	
	@Query("select l from Lessor l where l.userAccount.id=?1")
	Lessor findByUserAccountId(int id);
	
	// Admin dashboard -----------------------------------------
	
	@Query("select 1.0*(select count(r1) from Request r1 where r1.status='ACCEPTED' and r1.property.lessor=r.property.lessor)/count(r) from Request r group by r.property.lessor")
	Double findAvgAcceptedRequestPerLessor();
	
	@Query("select 1.0*(select count(r1) from Request r1 where r1.status='DENIED' and r1.property.lessor=r.property.lessor)/count(r) from Request r group by r.property.lessor")
	Double findAvgDeniedRequestPerLessor();
	
	@Query("select r.property.lessor from Request r where r.status='ACCEPTED' group by r.property.lessor having count(r)>=all(select count(c) from Request c where c.status='ACCEPTED' group by c.property.lessor)")
	Collection<Lessor> findLessorsMoreApprovedRequest();
	
	@Query("select r.property.lessor from Request r where r.status='DENIED' group by r.property.lessor having count(r)>=all(select count(c) from Request c where c.status='DENIED' group by c.property.lessor)")
	Collection<Lessor> findLessorsMoreDeniedRequest();
	
	@Query("select r.property.lessor from Request r where r.status='PENDING' group by r.property.lessor having count(r)>=all(select count(c) from Request c where c.status='PENDING' group by c.property.lessor)")
	Collection<Lessor> findLessorsMorePendingRequest();
	
	


}


