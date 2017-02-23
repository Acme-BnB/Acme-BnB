package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aj.org.objectweb.asm.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer>{

}
