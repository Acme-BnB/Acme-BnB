
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Invoice extends DomainEntity {

	// Constructors --------------------------

	public Invoice() {
		super();
	}


	// Attributes ----------------------------

	private Date	issudeMoment;
	private Integer	vat;
	private String	information;
	private String	detail;
	private Double	amountDue;


	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getIssudeMoment() {
		return issudeMoment;
	}
	public void setIssudeMoment(Date issudeMoment) {
		this.issudeMoment = issudeMoment;
	}

	public Integer getVat() {
		return vat;
	}
	public void setVat(Integer vat) {
		this.vat = vat;
	}

	@NotBlank
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}

	@NotBlank
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Digits(fraction = 2, integer = 7)
	public Double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(Double amountDue) {
		this.amountDue = amountDue;
	}


	// Relationships -------------------------------

	private Tenant	tenant;


	@Valid
	@ManyToOne(optional = false)
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

}
