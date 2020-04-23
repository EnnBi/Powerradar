package com.stie.powerradar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PowerradarApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerradarApplication.class, args);
		
		System.getenv().forEach((k,v)->
		System.err.println(k+"--"+v) 
		);//System.get("AWS_ACCESS_KEY"));
		System.err.println(System.getenv("AWS_SECRET_KEY"));
	}

}  
