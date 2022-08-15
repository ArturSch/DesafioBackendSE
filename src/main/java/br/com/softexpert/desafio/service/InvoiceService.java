package br.com.softexpert.desafio.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softexpert.desafio.domain.Invoice;
import br.com.softexpert.desafio.domain.InvoiceRecord;
import br.com.softexpert.desafio.domain.PaymentTransaction;
import br.com.softexpert.desafio.repository.InvoiceRepository;

@Service
public class InvoiceService {
	
	 private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private FriendService friendService ;
    
    @Autowired 
   private PatmentServiceFactory  patmentServiceFactory ;


    public Collection<PaymentTransaction> createInvoice(Invoice invoice) {
    	
    	List<InvoiceRecord> invoiceRecords = invoice.getInvoiceRecords();
    	
    	Map<Long, PaymentTransaction>paymentTransactionMap = new HashMap<Long, PaymentTransaction>();
    	
    	
    	double amount = 0.0 ;
    	for(InvoiceRecord  invoiceRecord : invoiceRecords ) {
    		
    		if(invoiceRecord.getFriendId() != null) {
    	
    		if(paymentTransactionMap.containsKey(invoiceRecord.getFriendId())) {
    			
    			PaymentTransaction paymentTransaction =paymentTransactionMap.get(invoiceRecord.getFriendId());

    			paymentTransaction.addAmount(invoiceRecord.getPrice());	
    			
    		}else {
    			PaymentTransaction paymentTransaction = new PaymentTransaction();
    			paymentTransaction.setFriend(friendService.getFriend(invoiceRecord.getFriendId()));
    			paymentTransaction.setTransactionAmount( BigDecimal.valueOf(invoiceRecord.getPrice()));
    			
    			paymentTransactionMap.put(invoiceRecord.getFriendId(), paymentTransaction);
        		
    			
    		}
    		}
    	
    		amount+=invoiceRecord.getPrice();
    		
    	}
    	
    	invoice.setTotalAmount(amount);
    	
    	if(invoice.getDiscountPercentage()>0) {
    		
    		invoice.calcDiscount();
    		
    	}
    	
    	
    	
    	
    	for(PaymentTransaction paymentTransaction :paymentTransactionMap.values()) {
    	   System.out.println(paymentTransaction.getFriend().getFirstName());
    		Double fairPercengae =   (paymentTransaction.getTransactionAmount().doubleValue() *100 )/amount ;
    		System.out.println("fairPercengae" + fairPercengae);
    	
    		paymentTransaction.addAmount((invoice.getDelivaryFee()*fairPercengae)/100);
    		System.out.println("delivary" + (invoice.getDelivaryFee()*fairPercengae)/100);
        	
    		paymentTransaction.subtractAmount((invoice.getDiscoutAmount()*fairPercengae)/100);
    		System.out.println("discount" + (invoice.getDiscoutAmount()*fairPercengae)/100);
    		
    		
    		System.out.println("amount" + paymentTransaction.getTransactionAmount().doubleValue());
    		
    		paymentTransaction.setTransactionAmount(paymentTransaction.getTransactionAmount().setScale(2, RoundingMode.HALF_UP));

    		System.out.println(" format  amount" + paymentTransaction.getTransactionAmount().doubleValue());
    		
    		paymentTransaction.setPaymentResponse(	 patmentServiceFactory.getService(paymentTransaction.getFriend().getPaymenttype()).processPayment(paymentTransaction));
    	
    	}
    	
    	
    	invoiceRepository.save(invoice);
    	
        return paymentTransactionMap.values() ; 
    }

  

    public void deleteInvoice(Long id) {
    	 Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
         if(invoiceOptional.isPresent()) {
        	 
        	 invoiceRepository.delete( invoiceOptional.get());
         }
       
    }

    
    public Iterable<Invoice> getAllInvoices() {
       
      
        return invoiceRepository.findAll();
    }
}
