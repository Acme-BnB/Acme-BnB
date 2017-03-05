package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import domain.Audit;
import domain.Auditor;




@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer>{
	
	@Query("select r from Audit r where r.auditor= ?1")
	Collection<Audit> findByCreator(Auditor t);
}

