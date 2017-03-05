
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Commentable extends DomainEntity {

	//Constructor --------------------------------

	// Attributes --------------------------------

	private Boolean	isCommentable	= false;


	public Boolean getIsCommentable() {
		return isCommentable;
	}

	public void setIsCommentable(Boolean isCommentable) {
		this.isCommentable = isCommentable;
	}


	// Relationships -----------------------------

	private Collection<Comment>	comments;


	@Valid
	@OneToMany(mappedBy = "commentable")
	public Collection<Comment> getcomments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

}
