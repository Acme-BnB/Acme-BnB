package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

	// Admin dashboard ------------------------------------------------
	
	@Query("select sum(i.amountDue) from Invoice i")
	Double findTotalAmountOfInvoices();
}
