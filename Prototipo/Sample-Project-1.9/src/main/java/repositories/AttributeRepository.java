package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Attribute;


@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer>{

	// Admin dashboard -----------------------------------------------
	
	@Query("select a from Attribute a order by a.values.size DESC")
	Collection<Attribute> findAttributesOrderByNumberTimesUsed();
	
	
}
