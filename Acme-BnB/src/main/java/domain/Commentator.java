
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Commentator extends Actor {

	// Constructors -----------------------------------------------------------

	public Commentator() {
		super();
	}


	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------

	private Collection<Comment>	writtenComments;


	@Valid
	@OneToMany(mappedBy = "commentator")
	public Collection<Comment> getWrittenComments() {
		return writtenComments;
	}
	public void setWrittenComments(Collection<Comment> writtenComments) {
		this.writtenComments = writtenComments;
	}

}
