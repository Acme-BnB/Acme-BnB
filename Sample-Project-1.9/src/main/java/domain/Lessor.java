package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Lessor extends Actor{
	
	// Constructor -----------------------------
	
	public Lessor(){
		super();
	}
	
	// Attributes ------------------------------
	
	private CreditCard creditCard;
	
	@Valid
	public CreditCard getCreditCard(){
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard){
		this.creditCard=creditCard;
	}
	
	// Relationships --------------------------
	
	private Collection<Comment> comments;
	private Collection<Property> properties;
	
	@Valid
	@OneToMany(mappedBy="comment")
	public Collection<Comment> getComments(){
		return comments;
	}
	public void setComments(Collection<Comment> comments){
		this.comments=comments;
	}
	
	@Valid
	@OneToMany(mappedBy="property")
	public Collection<Property> getProperties(){
		return properties;
	}
	public void setProperties(Collection<Property> properties){
		this.properties = properties;
	}

}
