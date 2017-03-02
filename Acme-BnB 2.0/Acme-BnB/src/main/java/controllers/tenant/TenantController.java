package controllers.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TenantService;
import domain.Tenant;

@Controller
@RequestMapping("/tenant")
public class TenantController {
	//Services-------------------------

		@Autowired
		private TenantService	tenantService;
		



		//Constructor----------------------

		public TenantController() {
			super();
		}

		//List--------------------------

		@RequestMapping(value="/display", method=RequestMethod.GET)
		public ModelAndView display(@RequestParam int tenantId) {
				ModelAndView result;
				Tenant tenant;
				
				tenant=tenantService.findOne(tenantId);
				
				result=new ModelAndView("tenant/display");
				result.addObject("tenant", tenant);
				result.addObject("comments", tenant.getcomments());
				return result;
		}
}
