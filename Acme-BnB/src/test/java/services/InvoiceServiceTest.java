package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Invoice;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
public class InvoiceServiceTest extends AbstractTest{

	//Service under test --------------------------
	@Autowired
	private InvoiceService invoiceService;
	
	//Tests ---------------------------------------
	//Positive ------------------------------------
	@Test
	public void testCreate(){
		Invoice invoice;
		invoice = invoiceService.create();
		Assert.notNull(invoice);
	}
	
	@Test
	public void testSave(){
		Invoice invoice, saved, invoiceG;
		invoice = invoiceService.findOne(61);
		invoice.setDetail("Detalle");
		saved = invoiceService.save(invoice);
		invoiceG = invoiceService.findOne(61);
		Assert.isTrue(saved.getDetail().equals(invoiceG.getDetail()));
	}

	@Test
	public void testFindOne(){
		Invoice invoice;
		invoice = invoiceService.findOne(61);
		Assert.notNull(invoice);
	}
	
	@Test
	public void testFindAll(){
		Collection<Invoice> invoices;
		invoices = invoiceService.findAll();
		Assert.notNull(invoices);
	}
	
}