package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {

	// Constructors -----------------------------------------------------------

	public Administrator() {
		super();
	}
	
	// Attributes -------------------------------------------------------------
	
	// Relationships ----------------------------------------------------------

	private Collection<Attribute> attributes;
	
	@Valid
	@OneToMany(mappedBy="administrator")
	public Collection<Attribute> getAttributes(){
		return attributes;
	}
	public void setAttributes(Collection<Attribute> attributes){
		this.attributes = attributes;
	}
	
	
	
}