package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer>{
	
	@Query("select t from Tenant t where t.name like %?1%")
	Collection<Tenant> findByKey(String key);
	
	@Query("select t from Tenant t where t.userAccount.id=?1")
	Tenant findByUserAccountId(int id);
	
	
}


