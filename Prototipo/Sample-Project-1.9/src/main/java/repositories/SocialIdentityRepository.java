package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;


import domain.SocialIdentity;

@Repository
public interface SocialIdentityRepository extends JpaRepository<SocialIdentity, Integer>{
	
	@Query("select s from SocialIdentity s where s.actor.id = ?1")
	SocialIdentity findByUserAccountId(int id);
	
	
}
