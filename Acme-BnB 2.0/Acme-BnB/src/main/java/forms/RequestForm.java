
package forms;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

import domain.CreditCard;

@Embeddable
@Access(AccessType.PROPERTY)
public class RequestForm {

	// Attributes ----------------------------------------------------

	private int 		propertyId;
	private int 		id;
	private Date		checkIn;
	private Date		checkOut;
	private Boolean		smoker;
	private CreditCard creditCard;
	private String		status;
	
	// Constructor --------------------------------------------------

	public RequestForm() {
		super();
	}

	// Getter y Setter ------------------
	
	
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	
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

	public Boolean getSmoker() {
		return smoker;
	}
	public void setSmoker(Boolean smoker) {
		this.smoker = smoker;
	}

	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	@NotBlank
	@Pattern(regexp = "^PENDING$|^ACCEPTED$|^DENIED$")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	public int getPropertyId(){
		return propertyId;
	}
	public void setPropertyId(int propertyId){
		this.propertyId=propertyId;
	}
	

}
