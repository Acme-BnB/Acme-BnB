
package controllers.tenant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;

import controllers.AbstractController;
import domain.Finder;
import forms.FinderForm;

@Controller
@RequestMapping("/tenant/finder")
public class TenantFinderController extends AbstractController {

	//Services-------------------------

	@Autowired
	private FinderService	finderService;

	

	//Constructor----------------------

	public TenantFinderController() {
		super();
	}

	//Display--------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {

		ModelAndView result;
		Finder finder;

		finder = finderService.findByPrincipal();

		result = new ModelAndView("finder/display");
		result.addObject("finder", finder);
		result.addObject("requestURI", "tenant/finder/display.do");

		return result;
	}

	//Creation-------------------------

	

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int finderId) {

		ModelAndView result;
		Finder finder;
		
		finder = finderService.findOne(finderId);
		FinderForm finderform=finderService.transform(finder);
		Assert.notNull(finderform);
		result = createEditModelAndView(finderform);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FinderForm finderForm, BindingResult binding) {

		ModelAndView result;
		Finder finder;
		if (binding.hasErrors()) {
			result = createEditModelAndView(finderForm);
		} else {
			try {
				finder=finderService.reconstruct(finderForm, binding);
				finderService.save(finder);
				result = display();
			} catch (Throwable oops) {
				result = createEditModelAndView(finderForm, "master.page.commit.error");
		}

		
		}
		return result;
	}

	

	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(FinderForm finderForm) {

		ModelAndView result;

		result = createEditModelAndView(finderForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(FinderForm finderForm, String message) {
		ModelAndView result;

		result = new ModelAndView("finder/edit");
		result.addObject("finder", finderForm);

		result.addObject("message", message);

		return result;

	}

}
