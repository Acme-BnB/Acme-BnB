
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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Audit extends DomainEntity {

	// Constructors ----------------------------

	public Audit() {
		super();
	}


	// Attributes ------------------------------

	private Date	writtenMoment;
	private String	text;
	private Integer	attachments;
	private Boolean	draft;


	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getWrittenMoment() {
		return writtenMoment;
	}
	public void setWrittenMoment(Date writtenMoment) {
		this.writtenMoment = writtenMoment;
	}

	@NotBlank
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Integer getAttachments() {
		return attachments;
	}
	public void setAttachments(Integer attachments) {
		this.attachments = attachments;
	}

	public Boolean getDraft() {
		return draft;
	}
	public void setDraft(Boolean draft) {
		this.draft = draft;
	}


	// Relationships --------------------------

	private Auditor		auditor;
	private Property	property;


	@Valid
	@ManyToOne(optional = false)
	public Auditor getAuditor() {
		return auditor;
	}
	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}

	@Valid
	@ManyToOne(optional = false)
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}

}
