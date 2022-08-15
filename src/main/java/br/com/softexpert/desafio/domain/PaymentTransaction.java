package br.com.softexpert.desafio.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "PAYMENT_TRANSACTION")
@EqualsAndHashCode
public class PaymentTransaction {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PAYMENT_TRANSACTION_ID")
	private Long paymentTransactionId;
	
	@Column(name="TRANSACTION_AMOUNT")
	private BigDecimal transactionAmount ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FRIEND_ID")
	private Friend friend;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PAYMENT_RESPONSE_ID")
	private PaymentResponse paymentResponse;
	
	
	public void addAmount(Double price) {
		
		transactionAmount =	transactionAmount.add(BigDecimal.valueOf(price));
		
	}
	
	public void subtractAmount(Double price) {
		
		transactionAmount =	transactionAmount.subtract(BigDecimal.valueOf(price));
		
	}
	  
	
}
