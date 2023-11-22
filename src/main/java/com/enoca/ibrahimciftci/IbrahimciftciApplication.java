package com.enoca.ibrahimciftci;

import com.enoca.ibrahimciftci.model.Customer;
import com.enoca.ibrahimciftci.model.Order;
import com.enoca.ibrahimciftci.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class IbrahimciftciApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbrahimciftciApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(CustomerRepository repository){
		return runner -> {
			addCustomer(repository);

		};
	}

	private void addCustomer(CustomerRepository repository) {
		Customer customer = new Customer("Ahmet", "Kaya", 25);
		Order order = new Order(new Date(), 200);

		customer.addOrder(order);
		repository.save(customer);
	}

}

