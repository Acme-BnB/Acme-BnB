
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

@Embeddable
@Access(AccessType.PROPERTY)
public class FeeForm {

	// Attributes ------------------------------------------------

	private int		id;

	private Double	value;


	// Constructor -----------------------------------------------

	public FeeForm() {
		super();
	}

	// Getters and Setters ------------------------------------------

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Min(0)
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}

}
