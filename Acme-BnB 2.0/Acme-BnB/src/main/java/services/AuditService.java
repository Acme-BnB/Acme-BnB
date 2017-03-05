
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AuditRepository;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Audit;
import domain.Auditor;
import domain.Property;

import forms.AuditForm;


@Service
@Transactional
public class AuditService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AuditRepository	auditRepository;

	@Autowired
	private AuditorService auditorService;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private Validator validator;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public AuditService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Audit create() {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("AUDITOR");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Auditor a=auditorService.findByPrincipal();
		Audit result;
		result = new Audit();
		result.setAuditor(a);
		return result;
	}

	public Collection<Audit> findAll() {
		Collection<Audit> result;

		result = auditRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Audit findOne(int auditId) {
		Audit result;

		result = auditRepository.findOne(auditId);
		Assert.notNull(result);

		return result;
	}

	public Audit save(Audit audit) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("AUDITOR");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(audit);

		Audit result;

		result = auditRepository.save(audit);

		return result;
	}

	public Audit save2(Audit audit) {

		Assert.notNull(audit);
		Audit result;
		result = auditRepository.save(audit);

		return result;
	}

	public void delete(Audit audit) {

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Authority au = new Authority();
		au.setAuthority("AUDITOR");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Assert.notNull(audit);
		Assert.isTrue(audit.getId() != 0);

		auditRepository.delete(audit);
	}
	public Collection<Audit> findNoDraftAudits(Property property){
		Collection<Audit>result=new ArrayList<Audit>();
		Collection<Audit>aux;
		aux=property.getAudits();
		for(Audit a:aux){
			if(a.getDraft()==false){
				result.add(a);
			}
		}
		return result;
	}
	public Collection<Audit> findByCreator(Auditor t) {
		Collection<Audit> result;
		result = auditRepository.findByCreator(t);
		
		return result;
	}
	// Form methods --------------------------------
	
		public AuditForm generateForm(){
				AuditForm result;
				result = new AuditForm();
				
				return result;
				}
				
		public Audit reconstruct(AuditForm auditForm,  BindingResult binding){
				Audit result;
				if(auditForm.getId() == 0){
					result = create();
					Property property;					
					property = propertyService.findOne(Integer.valueOf(auditForm.getPropertyId()));
					result.setProperty(property);
				}else{
					result=findOne(auditForm.getId());
					
				}
				result.setText(auditForm.getText());
				result.setDraft(true);
				Date d=new Date(System.currentTimeMillis()-1000);
				result.setWrittenMoment(d);
				validator.validate(result, binding);
				return result;				
				}
		
		public AuditForm transform(Audit audit){
			AuditForm result=generateForm();
			result.setId(audit.getId());
			result.setDraft(audit.getDraft());
			result.setPropertyId(audit.getProperty().getId());
			result.setText(audit.getText());
			result.setWrittenMoment(audit.getWrittenMoment());
			return result;
		}
		

}
