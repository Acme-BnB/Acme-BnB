package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Auditor;

@Repository
public interface AuditorRepository extends JpaRepository<Auditor, Integer>{
	
	@Query("select a from Auditor a where a.companyName like %?1%")
	Collection<Auditor> findByKey(String key);
	
	@Query("select a from Auditor a where a.userAccount.id=?1")
	Auditor findByUserAccountId(int id);
	
	
}


