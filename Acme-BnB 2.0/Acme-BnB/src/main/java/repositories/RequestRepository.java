
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Request;
import domain.Tenant;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

	@Query("select r from Request r where r.tenant= ?1")
	Collection<Request> findByCreator(Tenant t);

	// Dashboard ------------------------------------------

	@Query("select count(r) from Request r where r.status='ACCEPTED' group by r.tenant having count(r) <= all(select count(c) from Request c where c.status='ACCEPTED' group by c.tenant)")
	Double minInvoicesIssuedToTenants();

	@Query("select 1.0*(select count(r) from Request r where r.status='ACCEPTED')/count(t) from Tenant t")
	Double avgInvoicesIssuedToTenants();

	@Query("select count(r) from Request r where r.status='ACCEPTED' group by r.tenant having count(r) >= all(select count(c) from Request c where c.status='ACCEPTED' group by c.tenant)")
	Double maxInvoicesIssuedToTenants();

}
