
package controllers.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RequestService;
import controllers.AbstractController;
import domain.CreditCard;
import domain.Invoice;
import domain.Request;

@Controller
@RequestMapping("/invoice")
public class TenantInvoiceController extends AbstractController {

	//Services----------------------------------------------

	@Autowired
	private RequestService	requestService;


	// Constructor -----------------------------------------

	public TenantInvoiceController() {
		super();
	}

	// Listing----------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int requestId) {

		ModelAndView result;
		Request request = requestService.findOne(requestId);
		Invoice invoice = request.getInvoice();
		CreditCard creditCard = request.getCreditCard();
		String credit;
		
		credit = "************"+creditCard.getNumber().substring(12);
		result = new ModelAndView("invoice/display");
		result.addObject("invoice", invoice);
		result.addObject("creditCard", credit);

		return result;

	}

}
