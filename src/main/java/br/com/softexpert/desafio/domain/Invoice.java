package br.com.softexpert.desafio.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "INVOICE")
public class Invoice {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="INVOICE_ID")
	private Long invoiceId;
	
	@Column(name="INVOICE_DATE")
	private Date invoiceDate = new Date();
	
	@Column(name="DELIVARY_FEE")
	private double delivaryFee  ;
	
	@Column(name="DISCOUNT_AMOUNT")
	private double discoutAmount ;
	
	@Column(name="DISCOUNT_PERCENTAGE")
	private long discountPercentage ;
	
	@Column(name="TOTAL_AMOUNT")
	private double totalAmount ;
	
	 @OneToMany(mappedBy = "invoice",
             orphanRemoval = true,
             fetch = FetchType.EAGER,
             cascade = CascadeType.ALL)
	private List<InvoiceRecord> invoiceRecords ;
	
	public void calcDiscount() {
		
		discoutAmount = (totalAmount*discountPercentage)/100;
		
	}

}
