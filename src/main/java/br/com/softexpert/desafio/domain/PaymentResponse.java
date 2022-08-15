package br.com.softexpert.desafio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "PAYMENT_RESPONSE")
public class PaymentResponse {
	@Id
	@Column(name = "PAYMENT_RESPONSE_ID")
	private Long paymentResponseId;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "DETAIL")
	private String detail;

	@Column(name = "QR_CODE_BASE64")
	private String qrCodeBase64;

	@Column(name = "QR_CODE")
	private String qrCode;

}
