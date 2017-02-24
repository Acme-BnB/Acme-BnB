package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer>{
	
	// Admin dashboard ------------------------------------
	
	@Query("select min(f.results.size) from Finder f")
	Double findMinResultPerFinder();
	
	@Query("select avg(f.results.size) from Finder f")
	Double findAvgResultPerFinder();
	
	@Query("select max(f.results.size) from Finder f")
	Double findMaxResultPerFinder();

}
