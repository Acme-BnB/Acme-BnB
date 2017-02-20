package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Value extends DomainEntity{
	// Constructor ---------------------
	public Value(){
		super();
	}
	
	// Attributes --------------------------------
	private String text;

	@NotBlank
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	// Relationships -----------------------------
}
