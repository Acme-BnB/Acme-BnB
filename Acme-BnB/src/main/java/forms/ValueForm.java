package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Attribute;

@Embeddable
@Access(AccessType.PROPERTY)
public class ValueForm {

	// Attributes ----------------------------------------------------
	
	private Attribute attribute;
	private String text;
	private int propertyId;
	
	@Valid
	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@NotNull
	public int getPropertyId(){
		return propertyId;
	}
	public void setPropertyId(int propertyId){
		this.propertyId=propertyId;
	}
	
}
