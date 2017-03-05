
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class SocialIdentityForm {

	// Attributes ----------------------------------------------------

	private int		id;
	private String	nick;
	private String	socialNetwork;
	private String	profileURL;


	// Constructor --------------------------------------------------

	public SocialIdentityForm() {
		super();
	}

	// Getters and Setters ------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSocialNetwork() {
		return socialNetwork;
	}
	public void setSocialNetwork(String socialNetwork) {
		this.socialNetwork = socialNetwork;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getProfileURL() {
		return profileURL;
	}
	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}

	@NotNull
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
