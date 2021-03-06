package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Attribute extends DomainEntity{
	// Constructors ----------------------------
	public Attribute(){
		super();
	}
	
	// Attributes -----------------------------
	
	private String name;
	
	@NotBlank
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	// Relationships --------------------------
	
	private Administrator administrator;
	private Collection<Property> properties;
	
	@Valid
	@ManyToOne(optional=false)
	public Administrator getAdministrator(){
		return administrator;
	}
	public void setAdministrator(Administrator administrator){
		this.administrator = administrator;
	}
	
	@Valid
	@OneToMany(mappedBy="attribute")
	public Collection<Property> getProperties(){
		return properties;
	}
	public void setProperties(Collection<Property> properties){
		this.properties=properties;
	}
}
