package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Lessor extends Commentator implements Commentable{
	
	// Constructor -----------------------------
	
	public Lessor(){
		super();
	}
	
	// Attributes ------------------------------
	
	private CreditCard creditCard;
	private Double feeAmount;
	
	@Valid
	public CreditCard getCreditCard(){
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard){
		this.creditCard=creditCard;
	}
	
	@Min(0)
	public Double getFeeAmount(){
		return feeAmount;
	}
	public void setFeeAmount(Double feeAmount){
		this.feeAmount=feeAmount;
	}
	
	// Relationships --------------------------
	
	private Collection<Property> properties;
	
	@Valid
	@OneToMany(mappedBy="property")
	public Collection<Property> getProperties(){
		return properties;
	}
	public void setProperties(Collection<Property> properties){
		this.properties = properties;
	}

}
