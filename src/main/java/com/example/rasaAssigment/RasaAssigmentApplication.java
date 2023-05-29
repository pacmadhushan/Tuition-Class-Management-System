package com.example.rasaAssigment;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RasaAssigmentApplication {

	public static void main(String[] args) {
		System.out.println("1111111111111111111111111");
		SpringApplication.run(RasaAssigmentApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
