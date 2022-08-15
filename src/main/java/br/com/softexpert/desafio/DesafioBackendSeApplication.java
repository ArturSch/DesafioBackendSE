package br.com.softexpert.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class DesafioBackendSeApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		
		SpringApplication springApplication = new SpringApplication(DesafioBackendSeApplication.class);
		
		springApplication.run(args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DesafioBackendSeApplication.class);
    }
	
}
