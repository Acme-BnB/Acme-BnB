
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {

	@Query("select t from Tenant t where t.name like %?1%")
	Collection<Tenant> findByKey(String key);

	@Query("select t from Tenant t where t.userAccount.id=?1")
	Tenant findByUserAccountId(int id);

	// Admin dashboard -----------------------------------------------

	@Query("select 1.0*(select count(r1) from Request r1 where r1.status='ACCEPTED')/count(r) from Tenant r")
	Double findAvgAcceptedRequestPerTenant();

	@Query("select 1.0*(select count(r1) from Request r1 where r1.status='DENIED')/count(r) from Tenant r")
	Double findAvgDeniedRequestPerTenant();

	@Query("select r.tenant from Request r where r.status='ACCEPTED' group by r.tenant having count(r) >= all(select count(c) from Request c where c.status='ACCEPTED' group by c.tenant)")
	Collection<Tenant> findTenantMoreApprovedRequest();

	@Query("select r.tenant from Request r where r.status='DENIED' group by r.tenant having count(r) >= all(select count(c) from Request c where c.status='DENIED' group by c.tenant)")
	Collection<Tenant> findTenantMoreDeniedRequest();

	@Query("select r.tenant from Request r where r.status='PENDING' group by r.tenant having count(r) >= all(select count(c) from Request c where c.status='PENDING' group by c.tenant)")
	Collection<Tenant> findTenantMorePendingRequest();

	@Query("select count(r) from Request r where r.status='ACCEPTED' group by r.tenant having count(r) <= all(select count(c) from Request c where c.status='ACCEPTED' group by c.tenant)")
	Double findMinNumberInvoiceToTheTenant();

	@Query("select 1.0*(select count(r) from Request r where r.status='ACCEPTED')/count(t) from Tenant t")
	Double findAvgNumberInvoiceToTheTenant();

	@Query("select count(r) from Request r where r.status='ACCEPTED' group by r.tenant having count(r) >= all(select count(c) from Request c where c.status='ACCEPTED' group by c.tenant)")
	Double findMaxNumberInvoiceToTheTenant();

	@Query("select distinct r1.tenant, 1.0*(select count(r) from Request r where r.status='ACCEPTED' and r.tenant=r1.tenant)/count(r1) from Request r1 group by r1.tenant")
	List<Object[]> maxMinRatio();

}
