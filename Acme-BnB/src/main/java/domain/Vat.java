package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Vat extends DomainEntity{
	// Constructor ------------------------------------------------------------
	
		public Vat(){
			super();
		}
		
		// Attributes -------------------------------------------------------------
		
		private String value;

		@NotBlank
		@Pattern(regexp = "([A-Z]{2})-\\d{2,12})")
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		// Relationships ----------------------------------------------------------
		
	}


