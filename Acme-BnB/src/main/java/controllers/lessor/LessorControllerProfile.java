
package controllers.lessor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LessorService;
import services.PropertyService;
import services.RequestService;
import domain.Lessor;
import domain.Property;
import domain.Request;
import forms.LessorForm;

@Controller
@RequestMapping("/lessor")
public class LessorControllerProfile {

	//Services-------------------------

	@Autowired
	private LessorService	lessorService;

	@Autowired
	private PropertyService	propertyService;

	@Autowired
	private RequestService	requestService;


	//Constructor----------------------

	public LessorControllerProfile() {
		super();
	}

	

	// Edit personal data ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Lessor lessor = lessorService.findByPrincipal();
		String tipe = "personal";

		LessorForm lessorForm = lessorService.generateForm(lessor);
		result = new ModelAndView("lessor/register");
		result.addObject("lessorForm", lessorForm);
		result.addObject("tipe", tipe);

		return result;
	}

		//List--------------------------

		@RequestMapping(value = "/displayByReq", method = RequestMethod.GET)
		public ModelAndView displayByReq(@RequestParam int requestId) {
			ModelAndView result;
			Request r;
			Lessor lessor;
			r = requestService.findOne(requestId);
			lessor = r.getProperty().getLessor();
			lessor = lessorService.encryptCreditCard(lessor);
			result = new ModelAndView("lessor/display");
			result.addObject("lessor", lessor);
			result.addObject("comments", lessor.getcomments());
			result.addObject("requestURI", "lessor/displayByReq.do");
			result.addObject("socialIdentities", lessor.getSocialIdentities());

			return result;
		}

		@RequestMapping(value="/display", method=RequestMethod.GET)
		public ModelAndView display(@RequestParam int propertyId) {
				ModelAndView result;
				Property property;
				Lessor lessor;
				
				property = propertyService.findOne(propertyId);
				lessor=property.getLessor();
				lessor = lessorService.encryptCreditCard(lessor);
				result=new ModelAndView("lessor/display");
				result.addObject("lessor", lessor);
				result.addObject("comments", lessor.getcomments());
				result.addObject("requestURI", "lessor/display.do");
				result.addObject("socialIdentities", lessor.getSocialIdentities());

				return result;
			}
		
		@RequestMapping(value="/displayL", method=RequestMethod.GET)
		public ModelAndView display() {
				ModelAndView result;
				Lessor lessor;

				
				lessor = lessorService.findByPrincipal();
				lessor = lessorService.encryptCreditCard(lessor);
				result=new ModelAndView("lessor/display");
				result.addObject("lessor", lessor);
				result.addObject("comments", lessor.getcomments());
				result.addObject("requestURI", "lessor/displayL.do");
				result.addObject("socialIdentities", lessor.getSocialIdentities());
				
				return result;
			}
		
		@RequestMapping(value="/displayById", method=RequestMethod.GET)
		public ModelAndView displayById(@RequestParam int lessorId) {
				ModelAndView result;
				Lessor lessor;
				
				lessor = lessorService.findOne(lessorId);
				lessor = lessorService.encryptCreditCard(lessor);
				result=new ModelAndView("lessor/display");
				result.addObject("lessor", lessor);
				result.addObject("comments", lessor.getcomments());
				result.addObject("requestURI", "lessor/displayById.do");
				result.addObject("socialIdentities", lessor.getSocialIdentities());

				return result;
			}

		

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid LessorForm lessorForm, BindingResult binding) {
		ModelAndView result;
		Lessor lessor;

		if (binding.hasErrors()) {
			result = createEditModelAndView(lessorForm);
		} else {
			try {
				lessor = lessorService.reconstructEditPersonalData(lessorForm, binding);
				lessorService.save2(lessor);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				String msgCode = "lessor.register.error";
				result = createEditModelAndView(lessorForm, msgCode);
			}
		}

		return result;
	}

	// Ancillary methods ---------------------------------------------------

	protected ModelAndView createEditModelAndView(LessorForm lessorForm) {
		ModelAndView result;

		result = createEditModelAndView(lessorForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(LessorForm lessorForm, String message) {
		ModelAndView result;

		result = new ModelAndView("lessor/register");
		result.addObject("lessorForm", lessorForm);
		result.addObject("message", message);

		return result;
	}

}
