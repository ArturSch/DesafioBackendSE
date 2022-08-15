package br.com.softExpert.desafioBackendSE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.softexpert.desafio.DesafioBackendSeApplication;
import br.com.softexpert.desafio.domain.Friend;
import br.com.softexpert.desafio.service.FriendService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DesafioBackendSeApplication.class)
@TestMethodOrder(OrderAnnotation.class)
 class FriendServiceTest{
	@Autowired
	 private FriendService friendService;

	  @Test
	  @Order(1) 
	     void testinit(){
	      
	     
	        assertTrue(friendService.getAllFriends().iterator().hasNext());
	      
	    }

	  
	  @Test
	  @Order(2) 
	     void testSaveFriend(){
	        //setup product
	        Friend friend = new Friend();
	        friend.setFriendId(5L);
	        friend.setFirstName("First");
	        friend.setLastName("Last");
	        friend.setKeyType("CPF");
	        friend.setPaymenttype("PIX");
	        friend.setEmail("Test@gmail.com");
	       
	        friend =  friendService.createFriend(friend);
	      
	        assertNotNull(friend.getFriendId());
	        
	        Friend fetchedFriend = friendService.getFriend(friend.getFriendId());

	        //should not be null
	        assertNotNull(fetchedFriend);

	        //should equal
	        assertEquals(friend.getFirstName(), fetchedFriend.getFirstName());
	     
	        //update description and save
	        fetchedFriend.setFirstName("first U");
	        friendService.updateFriend(fetchedFriend);

	        //get from DB, should be updated
	        Friend fetchedUpdatedFriend = friendService.getFriend(fetchedFriend.getFriendId());
	        assertEquals(fetchedUpdatedFriend.getFirstName(), fetchedFriend.getFirstName());

	        //verify count of products in DB
	     
	        assertTrue(friendService.getAllFriends().iterator().hasNext());

	        
	    }
	  @Test
	  @Order(3) 
	   void testCount(){
	      
		     
		  Iterable<Friend> friends = friendService.getAllFriends();

	        int count = 0;

	        for(Friend p : friends){
	            count++;
	        }
	        assertEquals(4, count);
	    }
	  
	  @Test
	  @Order(4) 
	     void testDeleteAll(){
	      
	     
	        assertTrue(friendService.getAllFriends().iterator().hasNext());
	        Iterable<Friend> friends = friendService.getAllFriends();

	       

	        for(Friend friend : friends){
	          friendService.deleteFriend(friend.getFriendId());
	        }
	        assertFalse(friendService.getAllFriends().iterator().hasNext());
	        
	    }

}
