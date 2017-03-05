package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Commentable;

@Repository
public interface CommentableRepository extends JpaRepository<Commentable, Integer>{
	
	@Query("select c from Commentable c where c.name like %?1% and c.isCommentable=true")
	Collection<Commentable> findByKey(String key);

	@Query("select c from Commentable c where c.id=?1 and c.isCommentable=true")
	Commentable findCommentableById(int id);
	
	
}


