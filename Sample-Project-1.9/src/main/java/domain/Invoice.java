package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Invoice extends DomainEntity{

	// Constructors --------------------------
	
	public Invoice(){
		super();
	}
	
	// Attributes ----------------------------
	
	private Date issudeMoment;
	private Integer VAT;
	private String information;
	private String detail;
	private Double amountDue;
	private CreditCard creditCard;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getIssudeMoment() {
		return issudeMoment;
	}
	public void setIssudeMoment(Date issudeMoment) {
		this.issudeMoment = issudeMoment;
	}
	
	public Integer getVAT() {
		return VAT;
	}
	public void setVAT(Integer vAT) {
		VAT = vAT;
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
	
	public Double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(Double amountDue) {
		this.amountDue = amountDue;
	}
	
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	
}
