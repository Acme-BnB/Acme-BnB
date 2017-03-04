
package forms;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;

import domain.CreditCard;

@Embeddable
@Access(AccessType.PROPERTY)
public class RequestForm {

	// Attributes ----------------------------------------------------

	private int 		propertyId;
	private Date		checkIn;
	private Date		checkOut;
	private CreditCard creditCard;
	private Boolean 	smoker;
	
	// Constructor --------------------------------------------------

	public RequestForm() {
		super();
	}

	// Getter y Setter ------------------
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
		
	public int getPropertyId(){
		return propertyId;
	}
	public void setPropertyId(int propertyId){
		this.propertyId=propertyId;
	}
	
	public Boolean getSmoker(){
		return smoker;
	}
	public void setSmoker(Boolean smoker){
		this.smoker=smoker;
	}

}
