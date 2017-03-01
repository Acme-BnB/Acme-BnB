package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity{

	// Constructors -----------------------------------------------------------
	
		public SocialIdentity() {
			super();
		}
			
		// Attributes -------------------------------------------------------------

		private String nick;
		private String socialNetwork;
		private String profileURL;
		
		@NotBlank
		public String getNick() {
			return nick;
		}
		public void setNick(String nick) {
			this.nick = nick;
		}
		
		@NotBlank
		public String getSocialNetwork() {
			return socialNetwork;
		}
		public void setSocialNetwork(String socialNetwork) {
			this.socialNetwork = socialNetwork;
		}
		
		@NotBlank
		@URL
		public String getProfileURL() {
			return profileURL;
		}
		public void setProfileURL(String profileURL) {
			this.profileURL = profileURL;
		}
		
		// Relationships ----------------------------------------------------------
		
		private Actor actor;

		@Valid
		@ManyToOne(optional=false)
		public Actor getActor(){
			return actor;
		}
		public void setActor(Actor actor){
			this.actor = actor;
		}
	
}
