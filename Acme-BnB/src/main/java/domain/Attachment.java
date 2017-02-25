package domain;

import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class Attachment extends DomainEntity{
	//Constructor
	public Attachment(){
		super();
	}
	//Attributes
	private String url;
	
	@NotBlank
	@URL
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	//RelationShips
	private Audit audit;
	
	@Valid
	@ManyToOne(optional=false)
	public Audit getAudit() {
		return audit;
	}
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
}
