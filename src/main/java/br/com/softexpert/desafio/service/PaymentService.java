package br.com.softexpert.desafio.service;

import br.com.softexpert.desafio.domain.PaymentResponse;
import br.com.softexpert.desafio.domain.PaymentTransaction;

public interface PaymentService {
	 PaymentResponse processPayment(PaymentTransaction  paymentTransaction);
	 
	 String getType();
}
