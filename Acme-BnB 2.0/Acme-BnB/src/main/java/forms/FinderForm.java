package forms;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@Access(AccessType.PROPERTY)
public class FinderForm {
	// Attributes ------------------------------
	private String	destinationCity;
	private Double	minPrice;
	private Double	maxPrice;
	private String	keyword;
	
	// Constructor --------------------------------------------------

		public FinderForm() {
			super();
		}
		@NotBlank
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getDestinationCity() {
			return destinationCity;
		}
		public void setDestinationCity(String destinationCity) {
			this.destinationCity = destinationCity;
		}

		@Min(0)
		@Digits(fraction = 2, integer = 7)
		
		public Double getMinPrice() {
			return minPrice;
		}
		public void setMinPrice(Double minPrice) {
			this.minPrice = minPrice;
		}

		@Digits(fraction = 2, integer = 7)
		
		public Double getMaxPrice() {
			return maxPrice;
		}
		public void setMaxPrice(Double maxPrice) {
			this.maxPrice = maxPrice;
		}
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

		
}
