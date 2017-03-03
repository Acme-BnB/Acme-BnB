package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class AttributeForm {
	// Attributes -----------------------------
		private String	name;
	// Constructors ----------------------------
		public AttributeForm() {
			super();
		}


		

	// Getters and Setters ------------------------------------------


		@NotBlank
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}



}
