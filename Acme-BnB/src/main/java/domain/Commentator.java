
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

	private Collection<Comment>	comments;


	@Valid
	@OneToMany(mappedBy = "commentator")
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

}
