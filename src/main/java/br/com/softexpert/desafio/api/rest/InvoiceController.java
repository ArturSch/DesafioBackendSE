package br.com.softexpert.desafio.api.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.softexpert.desafio.domain.Invoice;
import br.com.softexpert.desafio.domain.PaymentTransaction;
import br.com.softexpert.desafio.exception.InvoiceNotFoundException;
import br.com.softexpert.desafio.service.InvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/desafio/v1/invoices")
@Api(tags = {"invoice"})
public class InvoiceController {

	 @Autowired
	    private InvoiceService invoiceService;

	
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		@ApiOperation(value = "Create a Invoice.", notes = "Returns the URL of the new resource in the Location header.")
		public @ResponseBody Collection<PaymentTransaction>createInvoice(@RequestBody Invoice invoice, HttpServletRequest request, HttpServletResponse response) {
		 return	 this.invoiceService.createInvoice(invoice);
				}

		@GetMapping
		@ResponseStatus(HttpStatus.OK)
		@ApiOperation(value = "Get a paginated list of all invoices.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
		public @ResponseBody Iterable<Invoice> getAllInvoice(HttpServletRequest request, HttpServletResponse response) {
			return this.invoiceService.getAllInvoices();
		}





		@DeleteMapping(value = "/{id}", produces = { "application/json",
				"application/xml" })
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@ApiOperation(value = "Delete a invoice resource.", notes = "You have to provide a valid invoice ID in the URL. Once deleted the resource can not be recovered.")
		public void deleteInvoice(
				@ApiParam(value = "The ID of the existing invoice resource.", required = true) @PathVariable("id") Long id,
				HttpServletRequest request, HttpServletResponse response) {
			
			this.invoiceService.deleteInvoice(id);
		}

		public static <T> T checkResourceFound(final T resource) {
			if (resource == null) {
				throw new InvoiceNotFoundException("resource not found");
			}
			return resource;
		}
	
}
