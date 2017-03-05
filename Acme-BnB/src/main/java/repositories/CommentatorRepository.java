package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Commentator;

@Repository
public interface CommentatorRepository extends JpaRepository<Commentator, Integer>{
	
	@Query("select c from Commentator c where c.name like %?1%")
	Collection<Commentator> findByKey(String key);
	
	@Query("select c from Commentator c where c.userAccount.username like ?1")
	Commentator findByUserAccount(String userName);
	
	
}


