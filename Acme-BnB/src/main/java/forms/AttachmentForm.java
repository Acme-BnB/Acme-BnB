package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Embeddable
@Access(AccessType.PROPERTY)
public class AttachmentForm {
	// Attributes ----------------------------------------------------
		
		private int 	auditId;
		private String	url;
		private int 	id;
		
	// Constructor --------------------------------------------------

		public AttachmentForm() {
			super();
		}

		
	
	// Getters and Setter---------------------------------------------
		public int getAuditId(){
			return auditId;
		}
		public void setAuditId(int auditId){
			this.auditId=auditId;
		}
		
		public int getId(){
			return id;
		}
		public void setId(int id){
			this.id=id;
		}

		@NotBlank
		@URL
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}

}
