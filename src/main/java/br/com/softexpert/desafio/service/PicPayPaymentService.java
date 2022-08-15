package br.com.softexpert.desafio.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import br.com.softexpert.desafio.domain.PaymentResponse;
import br.com.softexpert.desafio.domain.PaymentTransaction;

@Service
public class PicPayPaymentService implements PaymentService {
    
	private static final String NOT_IMPLEMENTD = "NOT IMPLEMENTED" ;
	
	private Random random = new Random();
    public PaymentResponse processPayment(PaymentTransaction  paymentTransaction) {
        

            return new PaymentResponse(
            		random.nextLong(),
                String.valueOf(NOT_IMPLEMENTD),
                NOT_IMPLEMENTD,
                NOT_IMPLEMENTD,
                NOT_IMPLEMENTD);
        
    }

	@Override
	public String getType() {
		
		return "PIC_PAY";
	}
}