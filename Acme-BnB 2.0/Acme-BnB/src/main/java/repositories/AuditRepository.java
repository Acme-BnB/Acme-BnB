package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import domain.Audit;
import domain.Auditor;



@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer>{
	@Query("select a from Audit a where a.auditor= ?1")
	Collection<Audit> findByCreator(Auditor a);
}
