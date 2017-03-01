
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	// Constructors ---------------------------

	public Finder() {
		super();
	}


	// Attributes ------------------------------

	private String	destinationCity;
	private Double	minPrice;
	private Double	maxPrice;
	private String	keyword;
	private Date	lastTimeSearched;


	@NotBlank
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

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getLastTimeSearched() {
		return lastTimeSearched;
	}
	public void setLastTimeSearched(Date lastTimeSearched) {
		this.lastTimeSearched = lastTimeSearched;
	}


	// Relationships ----------------------------

	private Collection<Property>	results;


	@Valid
	@ManyToMany
	public Collection<Property> getResults() {
		return results;
	}
	public void setResults(Collection<Property> results) {
		this.results = results;
	}

}
