package br.com.softexpert.desafio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.softexpert.desafio.domain.Friend;

@Repository
public interface  FriendRepository extends   CrudRepository<Friend ,Long>{

}
