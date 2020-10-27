package com.example.ch15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Ch15Application {
	@Autowired
	CustomerRepository customerRepository;

	private static final Logger log = LoggerFactory.getLogger(Ch15Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Ch15Application.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		log.info("[Find All]");
		List<CustomerEntity> customers = customerRepository.findAll();
		customers.forEach((e) -> {
			log.info(e.toString());
		});

		log.info("[Find By Name]");
		List<CustomerEntity> customers2 = customerRepository.findByName("한대영");
		customers2.forEach((e) -> {
			log.info(e.toString());
		});

		log.info("[Insert]");
		CustomerEntity customer = new CustomerEntity();
		customer.setName("홍길동");
		customer.setAddress("부산시");
		customer.setEmail("gildong@gmail.com");
		customerRepository.save(customer);

		log.info("[Find All Again]");
		List<CustomerEntity> customers3 = customerRepository.findAll();
		customers3.forEach((e) -> {
			log.info(e.toString());
		});

		return (args) -> {
			log.info("args : " + args);
		};
	}
}
