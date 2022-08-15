package br.com.softexpert.desafio.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.softexpert.desafio.domain.Invoice;


@Repository
public interface InvoiceRepository extends   CrudRepository<Invoice,Long> {

}
