
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class PropertyForm {

	// Attributes ----------------------------------------------------

	private int 		id;
	private String		name;
	private Double		rate;
	private String		description;
	private String		address;
	
	// Constructor --------------------------------------------------

	public PropertyForm() {
		super();
	}

	// Getters and Setters ------------------------------------------
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Min(0)
	@Digits(fraction = 2, integer = 3)
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@NotNull
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

}
