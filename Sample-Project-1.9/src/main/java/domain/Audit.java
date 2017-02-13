package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Audit extends DomainEntity{
	
	// Constructors ----------------------------
	
	public Audit(){
		super();
	}

	// Attributes ------------------------------
	
	private Date writtenMoment;
	private String text;
	private Integer attachments;
	private Boolean draft;
	
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
}
