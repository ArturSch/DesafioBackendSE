package br.com.softExpert.desafioBackendSE;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.softexpert.desafio.DesafioBackendSeApplication;
import br.com.softexpert.desafio.domain.Friend;
import br.com.softexpert.desafio.domain.Invoice;
import br.com.softexpert.desafio.domain.InvoiceRecord;
import br.com.softexpert.desafio.domain.PaymentTransaction;
import br.com.softexpert.desafio.service.InvoiceService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DesafioBackendSeApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class InvoiceServiceTest {
	
	@Autowired
	 private InvoiceService invoiceService;
	
	  @Test
	  @Order(1) 
	     void testinit(){
	      
	        assertFalse(invoiceService.getAllInvoices().iterator().hasNext());
	      
	    }
	  

	  
	  @Test
	  @Order(2) 
	     void testCreateInvoiceWithOneFriend(){
	      
	     
	       Invoice testInvoice = new Invoice() ;
	       testInvoice.setDelivaryFee(8);
	       testInvoice.setDiscoutAmount(20);
	       
	       InvoiceRecord  record1 = new InvoiceRecord();
	       record1.setOrderName("Hamburguer");
	       record1.setPrice(40);
	       record1.setFriendId(null);

	       
	       InvoiceRecord  record2 = new InvoiceRecord();
	       record2.setOrderName("Sobremesa");
	       record2.setPrice(2);
	       record2.setFriendId(null);
	       
	       
	       InvoiceRecord  record3 = new InvoiceRecord();
	       record3.setOrderName("Sanduíche");
	       record3.setPrice(8);
	       record3.setFriendId(1L);
	       
	       List<InvoiceRecord> invoiceRecords = new ArrayList<>();
	       invoiceRecords.add(record1);
	       invoiceRecords.add(record2);
	       invoiceRecords.add(record3);
	       
	       testInvoice.setInvoiceRecords(invoiceRecords);
	       
	       Collection<PaymentTransaction>paymentTransactions =  invoiceService.createInvoice(testInvoice);
	   
	       
	       assertEquals(1L,paymentTransactions.size() );
	    	   
	      
	      for( PaymentTransaction paymentTransaction : paymentTransactions) {
	    	  
	    	  assertEquals(6.08, paymentTransaction.getTransactionAmount().doubleValue());
	    	  assertNotNull(paymentTransaction.getPaymentResponse());
	    	  assertNotNull(paymentTransaction.getPaymentResponse().getQrCode());
	    	  assertNotNull(paymentTransaction.getPaymentResponse().getDetail());
	    	  assertNotNull(paymentTransaction.getPaymentResponse().getQrCodeBase64());
	    	  assertNotNull(paymentTransaction.getPaymentResponse().getStatus());
	      }
	      
	    }
	  
	  
	  @Test
	  @Order(3) 
	     void testCreateInvoiceWithTwoFriend(){
	      
	     
	       Invoice testInvoice = new Invoice() ;
	       testInvoice.setDelivaryFee(8);
	       testInvoice.setDiscountPercentage(20);
	       
	       InvoiceRecord  record1 = new InvoiceRecord();
	       record1.setOrderName("Hamburguer");
	       record1.setPrice(40);
	       record1.setFriendId(null);

	       
	       InvoiceRecord  record2 = new InvoiceRecord();
	       record2.setOrderName("Sobremesa");
	       record2.setPrice(2);
	       record2.setFriendId(null);
	       
	       
	       InvoiceRecord  record3 = new InvoiceRecord();
	       record3.setOrderName("Sanduíche");
	       record3.setPrice(8);
	       record3.setFriendId(1L);
	       
	       InvoiceRecord  record4 = new InvoiceRecord();
	       record4.setOrderName("coca cola");
	       record4.setPrice(3);
	       record4.setFriendId(1L);
	       
	       InvoiceRecord  record5 = new InvoiceRecord();
	       record5.setOrderName("Sanduíche");
	       record5.setPrice(8);
	       record5.setFriendId(3L);
	       
	       List<InvoiceRecord> invoiceRecords = new ArrayList<>();
	       invoiceRecords.add(record1);
	       invoiceRecords.add(record2);
	       invoiceRecords.add(record3);
	       invoiceRecords.add(record4); 
	       invoiceRecords.add(record5);	 
	       
	       testInvoice.setInvoiceRecords(invoiceRecords);
	       
	       Collection<PaymentTransaction>paymentTransactions =  invoiceService.createInvoice(testInvoice);
	   
	       
	       assertEquals(2L,paymentTransactions.size() );
	    	   
	      
	      for( PaymentTransaction paymentTransaction : paymentTransactions) {
	    	  
	    	
	    	  assertNotNull(paymentTransaction.getPaymentResponse());
	    	  assertNotNull(paymentTransaction.getPaymentResponse().getQrCode());
	    	  assertNotNull(paymentTransaction.getPaymentResponse().getDetail());
	    	  assertNotNull(paymentTransaction.getPaymentResponse().getQrCodeBase64());
	    	  assertNotNull(paymentTransaction.getPaymentResponse().getStatus());
	      }
	      
	    }
	  
	  @Test
	  @Order(4) 
	     void testDeleteAll(){
		
		  Iterable<Invoice> invoices =invoiceService.getAllInvoices();
	        assertTrue(invoices.iterator().hasNext());
	       

	       

	        for(Invoice invoice : invoices){
	        	invoiceService.deleteInvoice(invoice.getInvoiceId());
	        }
	        
	        
	        assertFalse(invoiceService.getAllInvoices().iterator().hasNext());
	        
	    }

	  

}
