
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InvoiceRepository;
import domain.Invoice;
import domain.Request;

@Service
@Transactional
public class InvoiceService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private InvoiceRepository	invoiceRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private RequestService		requestService;

	@Autowired
	private VatService			vatService;


	// Constructors -----------------------------------------------------------

	public InvoiceService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Invoice create() {

		Invoice result;
		result = new Invoice();

		return result;
	}

	public Collection<Invoice> findAll() {
		Collection<Invoice> result;

		result = invoiceRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Invoice findOne(int invoiceId) {
		Invoice result;

		result = invoiceRepository.findOne(invoiceId);
		Assert.notNull(result);

		return result;
	}

	public Invoice save(Invoice invoice) {

		Assert.notNull(invoice);

		Invoice result;

		result = invoiceRepository.save(invoice);

		return result;
	}

	public void delete(Invoice invoice) {

		Assert.notNull(invoice);
		Assert.isTrue(invoice.getId() != 0);

		invoiceRepository.delete(invoice);
	}

	// Other business services --------------------------------------

	public Double findTotalAmountOfInvoice() {
		Double result;

		result = invoiceRepository.findTotalAmountOfInvoices();
		return result;
	}

	public Invoice generateInvoice(int requestId) {

		Invoice result;
		Request request = requestService.findOne(requestId);
		String details = "Invoice of the property in " + request.getProperty().getAddress() + ", the owner is " + request.getTenant().getSurname() + ", " + request.getTenant().getName() + ".";
		String tenantInfo = "Name of tenant: " + request.getTenant().getSurname() + ", " + request.getTenant().getSurname() + "\nContact info:\nPhone: " + request.getTenant().getPhone() + "\nEmail: " + request.getTenant().getEmail();
		Date date = new Date(System.currentTimeMillis() - 1);
		Double dias = 1.0 * (request.getCheckOut().getTime() - request.getCheckIn().getTime()) / (1000 * 60 * 60 * 24);
		Double amount = request.getProperty().getRate() * dias;

		result = create();
		result.setVatNumber(vatService.findOne(2).getValue());
		result.setDetail(details);
		result.setInformation(tenantInfo);
		result.setissuedMoment(date);
		result.setAmountDue(amount);

		return save(result);

	}

}
