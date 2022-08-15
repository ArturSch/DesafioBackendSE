package br.com.softexpert.desafio.domain;

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

@Data
@Entity
@Table(name = "INVOICE_RECORD")
public class InvoiceRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INVOICE_RECORD_ID")
	private Long invoiceRecordId;

	@Column(name = "FRIEND_ID")
	private Long friendId;

	@Column(name = "ORDER_NAME")
	private String orderName;
	
	@Column(name = "PRICE")
	private double price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "INVOICE_ID")
	private Invoice invoice;

}
