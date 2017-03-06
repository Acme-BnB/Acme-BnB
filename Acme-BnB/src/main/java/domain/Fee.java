package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Fee extends DomainEntity{

	// Constructor ------------------------------------------------------------
	
	public Fee(){
		super();
	}
	
	// Attributes -------------------------------------------------------------
	
	private Double value;

	@Min(0)
	@Digits(fraction = 2, integer = 3)
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	// Relationships ----------------------------------------------------------
}
