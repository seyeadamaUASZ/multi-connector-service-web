package com.sid.gl;

import com.sid.gl.entities.Customer;
import com.sid.gl.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDataServiceApplication.class, args);
	}

	@Bean
    CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(Customer.builder()
							.name("adama")
							.email("adama@gmail.com")
					.build());
			customerRepository.save(Customer.builder()
					.name("assane")
					.email("assane@gmail.com")
					.build());
			customerRepository.save(Customer.builder()
					.name("bamba")
					.email("bamba@gmail.com")
					.build());
			customerRepository.save(Customer.builder()
					.name("Mohamed")
					.email("Mohamed@gmail.com")
					.build());
		};
	}
}
