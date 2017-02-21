
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	// Constructors -----------------------------

	public Comment() {
		super();
	}


	// Attributes-------------------------------

	private String	title;
	private Date	postedMoment;
	private String	text;
	private Integer	stars;


	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getPostedMoment() {
		return postedMoment;
	}
	public void setPostedMoment(Date postedMoment) {
		this.postedMoment = postedMoment;
	}

	@NotBlank
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Range(min = 0, max = 5)
	public Integer getStars() {
		return stars;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}


	// Relationship ------------------------------

	private Commentator	commentator;


	@Valid
	@ManyToOne(optional = false)
	public Commentator getCommentator() {
		return commentator;
	}
	public void setCommentator(Commentator commentator) {
		this.commentator = commentator;
	}

}
