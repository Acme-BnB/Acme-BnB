
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Tenant extends Commentator implements Commentable {

	// Constructors -------------------------------

	public Tenant() {
		super();
	}


	// Attributes --------------------------------

	// Relationships -----------------------------

	private Collection<Request>	requests;
	private Collection<Invoice>	invoices;
	private Finder				finder;


	@Valid
	@OneToMany(mappedBy = "tenant")
	public Collection<Request> getRequests() {
		return requests;
	}
	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}

	@Valid
	@OneToMany(mappedBy = "tenant")
	public Collection<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(Collection<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Valid
	@OneToOne(optional = false)
	public Finder getFinder() {
		return finder;
	}
	public void setFinder(Finder finder) {
		this.finder = finder;
	}

}