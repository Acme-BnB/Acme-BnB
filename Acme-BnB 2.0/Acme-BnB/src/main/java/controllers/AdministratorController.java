
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Credentials;
import services.AdministratorService;
import services.AttributeService;
import services.FinderService;
import services.LessorService;
import services.PropertyService;
import services.TenantService;
import domain.Administrator;
import domain.Attribute;
import domain.Lessor;
import domain.Property;
import domain.Tenant;
import forms.AdministratorForm;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private LessorService			lessorService;

	@Autowired
	private TenantService			tenantService;

	@Autowired
	private FinderService			finderService;

	@Autowired
	private PropertyService			propertyService;

	@Autowired
	private AttributeService		attributeService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Creation ------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create(@Valid @ModelAttribute Credentials credentials) {
		ModelAndView result;
		AdministratorForm administratorForm;

		administratorForm = administratorService.generateForm();
		result = createEditModelAndView(administratorForm);
		result.addObject("credentials", credentials);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AdministratorForm administratorForm, BindingResult binding) {
		ModelAndView result;
		Administrator administrator;
		Credentials credentials = new Credentials();
		if (binding.hasErrors()) {
			result = createEditModelAndView(administratorForm);
		} else {
			try {
				administrator = administratorService.reconstruct(administratorForm);
				administratorService.save2(administrator);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (Throwable oops) {
				String msgCode = "administrator.register.error";
				if (oops.getMessage().equals("notEqualPassword")) {
					msgCode = "administrator.register.notEqualPassword";
				} else {
					if (oops.getMessage().equals("agreedNotAccepted")) {
						msgCode = "administrator.register.agreedNotAccepted";
					}
				}
				result = createEditModelAndView(administratorForm, msgCode, credentials);
			}
		}

		return result;

	}

	// Dashboard -----------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView result;

		//Request
		Collection<Double> adrL = lessorService.findAvgAcceptedAndDeniedPerLessor();
		Collection<Double> adrT = tenantService.findAvgAcceptedAndDeniedPerTenant();

		Collection<Lessor> lamR = lessorService.findLessorsMoreApprovedRequest();
		Collection<Lessor> ldmR = lessorService.findLessorsMoreDeniedRequest();
		Collection<Lessor> lpmR = lessorService.findLessorsMorePendingRequest();
		Collection<Tenant> tamR = tenantService.findTenantMoreApprovedRequest();
		Collection<Tenant> tdmR = tenantService.findTenantMoreDeniedRequest();
		Collection<Tenant> tpmR = tenantService.findTenantMorePendingRequest();

		Collection<Lessor> marL = lessorService.maxRatioLessor();
		Collection<Lessor> mirL = lessorService.minRatioLessor();

		Collection<Tenant> marT = tenantService.maxRatioTenant();
		Collection<Tenant> mirT = tenantService.minRatioTenant();

		Collection<Double> ammrF = finderService.findAvgMinMaxResultPerFinder();

		Collection<Double> mamAP = propertyService.findMinAvgMaxAuditsPerProperty();
		Collection<Attribute> asd = attributeService.findAttributesOrderByNumberTimesUsed();

		result = new ModelAndView("administrator/dashboard");

		result.addObject("adrL", adrL);
		result.addObject("adrT", adrT);
		result.addObject("lamR", lamR);
		result.addObject("ldmR", ldmR);
		result.addObject("lpmR", lpmR);
		result.addObject("tamR", tamR);
		result.addObject("tdmR", tdmR);
		result.addObject("tpmR", tpmR);

		result.addObject("marL", marL);
		result.addObject("mirL", mirL);

		result.addObject("marT", marT);
		result.addObject("mirT", mirT);

		result.addObject("ammrF", ammrF);

		result.addObject("mamAP", mamAP);
		result.addObject("asd", asd);

		return result;

	}

	@RequestMapping(value = "/lessor", method = RequestMethod.GET)
	public ModelAndView listLessor() {

		ModelAndView result;

		Collection<Lessor> lessors = lessorService.findAll();

		result = new ModelAndView("administrator/lessor");
		result.addObject("lessor", lessors);

		return result;

	}

	@RequestMapping(value = "/dashboardLessor", method = RequestMethod.GET)
	public ModelAndView dashboardLessor(@RequestParam int lessorId) {

		ModelAndView result;

		Collection<Property> psA = propertyService.findPropertiesOfALessorOrderByNumberAudit(lessorId);
		Collection<Property> psR = propertyService.findPropertiesOfALessorOrderByNumberRequest(lessorId);
		Collection<Property> psAp = propertyService.findPropertiesOfALessorOrderByNumberRequestAccepted(lessorId);
		Collection<Property> psDn = propertyService.findPropertiesOfALessorOrderByNumberRequestDenied(lessorId);
		Collection<Property> psPn = propertyService.findPropertiesOfALessorOrderByNumberRequestPending(lessorId);

		result = new ModelAndView("administrator/dashboardLessor");

		result.addObject("psA", psA);
		result.addObject("psR", psR);
		result.addObject("psAp", psAp);
		result.addObject("psDn", psDn);
		result.addObject("psPn", psPn);

		return result;

	}
	// Ancillary methods ---------------------------------------

	protected ModelAndView createEditModelAndView(AdministratorForm administratorForm) {
		ModelAndView result;
		Credentials credentials = new Credentials();

		result = createEditModelAndView(administratorForm, null, credentials);

		return result;

	}

	protected ModelAndView createEditModelAndView(AdministratorForm administratorForm, String message, @Valid @ModelAttribute Credentials credentials) {
		ModelAndView result;

		result = new ModelAndView("administrator/register");
		result.addObject("administratorForm", administratorForm);
		result.addObject("message", message);
		result.addObject("credentials", credentials);

		return result;
	}

}
