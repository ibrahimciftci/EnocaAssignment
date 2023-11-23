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
			addCustomerWithOrder(repository);

		};
	}

	private void addCustomerWithOrder(CustomerRepository repository) {
		Customer customer1 = new Customer("customer1", "customer1", 15);
		Customer customer2 = new Customer("customer2", "customer2", 20);
		Customer customer3 = new Customer("customer3", "customer3", 24);
		Customer customer4 = new Customer("customer4", "customer4", 29);
		Customer customer5 = new Customer("customer5", "customer5", 23);


		Order order1 = new Order(new Date(), 123);
		Order order2 = new Order(new Date(), 456);
		Order order3 = new Order(new Date(), 789);
		Order order4 = new Order(new Date(), 159);
		Order order5 = new Order(new Date(), 357);
		Order order6 = new Order(new Date(), 852);
		Order order7 = new Order(new Date(), 258);
		Order order8 = new Order(new Date(), 456);
		Order order9 = new Order(new Date(), 654);
		Order order10 = new Order(new Date(), 951);
		Order order11 = new Order(new Date(), 458);
		Order order12 = new Order(new Date(), 658);
		Order order13 = new Order(new Date(), 964);
		Order order14 = new Order(new Date(), 428);
		Order order15 = new Order(new Date(), 278);

		customer1.addOrder(order1);
		customer1.addOrder(order2);
		customer1.addOrder(order3);

		customer2.addOrder(order4);
		customer2.addOrder(order5);
		customer2.addOrder(order6);

		customer3.addOrder(order7);
		customer3.addOrder(order8);
		customer3.addOrder(order9);

		customer4.addOrder(order10);
		customer4.addOrder(order11);
		customer4.addOrder(order12);

		customer5.addOrder(order13);
		customer5.addOrder(order14);
		customer5.addOrder(order15);


		repository.save(customer1);
		repository.save(customer2);
		repository.save(customer3);
		repository.save(customer4);
		repository.save(customer5);
	}

}

