package br.com.softexpert.desafio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;

import br.com.softexpert.desafio.domain.PaymentResponse;
import br.com.softexpert.desafio.domain.PaymentTransaction;
import br.com.softexpert.desafio.exception.PaymentException;

@Service
public class PixPaymentService implements PaymentService {
    @Value("${mercado_pago_sample_access_token}")
    private String mercadoPagoAccessToken;

    public PaymentResponse processPayment(PaymentTransaction  paymentTransaction) {
        try {
        	
        
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

            PaymentClient paymentClient = new PaymentClient();

            PaymentCreateRequest paymentCreateRequest =
                PaymentCreateRequest.builder()
                    .transactionAmount(paymentTransaction.getTransactionAmount())
                      .paymentMethodId("pix")
                    .payer(
                        PaymentPayerRequest.builder()
                            .email(paymentTransaction.getFriend().getEmail())
                            .firstName(paymentTransaction.getFriend().getFirstName())
                            .lastName(paymentTransaction.getFriend().getLastName())
                            .identification(
                                IdentificationRequest.builder()
                                    .type(paymentTransaction.getFriend().getKeyType())
                                    .number(paymentTransaction.getFriend().getNumber())
                                    .build())
                            .build())
                    .build();

            Payment createdPayment = paymentClient.create(paymentCreateRequest);

            return new PaymentResponse(
                createdPayment.getId(),
                String.valueOf(createdPayment.getStatus()),
                createdPayment.getStatusDetail(),
                createdPayment.getPointOfInteraction().getTransactionData().getQrCodeBase64(),
                createdPayment.getPointOfInteraction().getTransactionData().getQrCode());
        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new PaymentException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new PaymentException(exception.getMessage());
        }
    }

	@Override
	public String getType() {
		
		return "PIX";
	}
}