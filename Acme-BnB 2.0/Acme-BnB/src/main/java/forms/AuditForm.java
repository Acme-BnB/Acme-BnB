package forms;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@Access(AccessType.PROPERTY)
public class AuditForm {
	// Attributes ----------------------------------------------------
		
		private int 	propertyId;
		private int 	id;
		
		private Date	writtenMoment;
		private String	text;
		private Boolean	draft;
		
	// Constructor --------------------------------------------------

		public AuditForm() {
			super();
		}

		
	
	// Getters and Setter---------------------------------------------
		public int getPropertyId(){
			return propertyId;
		}
		public void setPropertyId(int propertyId){
			this.propertyId=propertyId;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
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
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
		public Boolean getDraft() {
			return draft;
		}
		public void setDraft(Boolean draft) {
			this.draft = draft;
		}
}
