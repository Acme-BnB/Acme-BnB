
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
import services.TenantService;
import controllers.AbstractController;
import domain.Finder;
import domain.Tenant;

@Controller
@RequestMapping("/tenant/finder")
public class TenantFinderController extends AbstractController {

	//Services-------------------------

	@Autowired
	private FinderService	finderService;

	@Autowired
	private TenantService	tenantService;


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

		result = new ModelAndView("tenant/display");
		result.addObject("finder", finder);
		result.addObject("requestURI", "tenant/finder/display.do");

		return result;
	}

	//Creation-------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Finder finder;

		finder = finderService.create();
		result = createEditModelAndView(finder);

		return result;

	}

	//Edition--------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int finderId) {

		ModelAndView result;
		Finder finder;

		finder = finderService.findOne(finderId);
		Assert.notNull(finder);
		result = createEditModelAndView(finder);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Finder finder, BindingResult binding) {

		ModelAndView result;
		Tenant tenant = tenantService.findByPrincipal();

		if (binding.hasErrors() || tenant.getFinder() != finder) {
			result = createEditModelAndView(finder);
		} else {
			try {
				finderService.save(finder);
				result = display();
			} catch (Throwable oops) {
				result = createEditModelAndView(finder, "master.page.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Finder finder, BindingResult binding) {

		ModelAndView result;

		try {
			finderService.delete(finder);
			result = display();
		} catch (Throwable oops) {
			result = createEditModelAndView(finder, "master.page.commit.error");
		}
		return result;
	}

	//Ancillary Methods---------------------------

	protected ModelAndView createEditModelAndView(Finder finder) {

		ModelAndView result;

		result = createEditModelAndView(finder, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Finder finder, String message) {
		ModelAndView result;

		result = new ModelAndView("finder/edit");
		result.addObject("finder", finder);

		result.addObject("message", message);

		return result;

	}

}
