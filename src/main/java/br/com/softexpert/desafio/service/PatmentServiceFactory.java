package br.com.softexpert.desafio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.softexpert.desafio.exception.PaymentException;

@Component
public class PatmentServiceFactory {

	
	 private static final Map<String, PaymentService> myServiceCache = new HashMap<>();

	    @Autowired
	    private PatmentServiceFactory(List<PaymentService> services) {
	        for(PaymentService service : services) {
	            myServiceCache.put(service.getType(), service);
	        }
	    }

	    public PaymentService getService(String type) {
	    	PaymentService service = myServiceCache.get(type);
	        if(service == null) throw new PaymentException("Unknown service type: " + type);
	        return service;
	    }
}
