package br.com.softexpert.desafio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "FRIEND")
public class Friend {

	@Id
	@Column(name="FRIEND_ID")
	private Long friendId;

	@NotNull
	@Column(name="FIRST_NAME")
	private String firstName;

	@NotNull
	@Column(name="LAST_NAME")
	private String lastName;

	@NotNull
	@Column(name="EMAIL")
	private String email;
	
	 @NotNull
	 @Column(name="PAYMENT_TYPE")
     private String paymenttype;
	
	 @NotNull
	 @Column(name="KEY_TYPE")
     private String keyType;

    @NotNull
    @Column(name="NUMBER")
    private String number;


	    
	    
}
