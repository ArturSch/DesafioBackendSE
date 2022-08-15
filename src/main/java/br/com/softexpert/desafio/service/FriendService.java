package br.com.softexpert.desafio.service;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softexpert.desafio.domain.Friend;
import br.com.softexpert.desafio.repository.FriendRepository;

@Service
public class FriendService {

	
	  private static final Logger log = LoggerFactory.getLogger(FriendService.class);

	    @Autowired
	    private FriendRepository hotelRepository;

	
	    public Friend createFriend(Friend friend) {
	        return hotelRepository.save(friend);
	    }

	    public Friend getFriend(long id) {
	         Optional<Friend> friendOptional = hotelRepository.findById(id);
	         if(friendOptional.isPresent()) {
	        	 
	        	 return friendOptional.get();
	         }
	         return null ;
	    }

	    public void updateFriend(Friend friend) {
	        hotelRepository.save(friend);
	    }

	    public void deleteFriend(Long id) {
	    	 Optional<Friend> friendOptional = hotelRepository.findById(id);
	         if(friendOptional.isPresent()) {
	        	 
	        	 hotelRepository.delete( friendOptional.get());
	         }
	       
	    }

	    
	    public Iterable<Friend> getAllFriends() {
	       
	      
	        return hotelRepository.findAll();
	    }
	
}
