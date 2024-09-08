package com.sj.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProductServiceConfig {	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}	
//	@Bean
//	public RestTemplate restTemplate () {
//		return new RestTemplate();
//	}

}
