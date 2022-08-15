package br.com.softExpert.desafioBackendSE;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.softexpert.desafio.DesafioBackendSeApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DesafioBackendSeApplication.class)
class DesafioBackendSeApplicationTests {

	 private MockMvc mvc;


	    @Autowired
	    WebApplicationContext context;
	 
	
	    
	  @Test
	    public void shouldHaveEmptyDB() throws Exception {
		  
		  mvc = MockMvcBuilders.webAppContextSetup(context).build();
		  
	        mvc.perform(get("/desafio/v1/friends")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()) ;
	        
	    
	              
	    }
}
