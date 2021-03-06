
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Lessor extends Commentator{

	// Constructor -----------------------------

	public Lessor() {
		super();
	}


	// Attributes ------------------------------

	private CreditCard	creditCard;
	private Double		feeAmount;


	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Min(0)
	@Digits(fraction = 2, integer = 7)
	public Double getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}


	// Relationships --------------------------

	private Collection<Property>	rProperties;


	@Valid
	@OneToMany(mappedBy = "lessor")
	public Collection<Property> getRProperties() {
		return rProperties;
	}
	public void setRProperties(Collection<Property> rProperties) {
		this.rProperties = rProperties;
	}

}
