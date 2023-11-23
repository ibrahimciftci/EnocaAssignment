package com.enoca.ibrahimciftci.service;

import com.enoca.ibrahimciftci.dto.CustomerDto;
import com.enoca.ibrahimciftci.model.Customer;
import com.enoca.ibrahimciftci.model.Order;
import com.enoca.ibrahimciftci.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    private final OrderService orderService;

    public CustomerServiceImpl(CustomerRepository customerRepository, OrderService orderService) {
        this.customerRepository = customerRepository;
        this.orderService = orderService;
    }

    @Override
    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll().stream().map(CustomerDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(Customer.fromDto(customerDto));
        return CustomerDto.fromModel(customer);
    }

    @Override
    public boolean deleteCustomer(int id) {
        Customer tempCustomer = findById(id);
        if (tempCustomer == null){
            throw new RuntimeException("Customer is not found!");
        }customerRepository.deleteById(id);
        return true;
    }


    @Override
    public CustomerDto updateCustomer(int id, CustomerDto customerDto) {
        Customer customer = findById(id);
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setAge(customerDto.getAge());
        customer.setOrders(customerDto.getOrders());

        customer = customerRepository.save(customer);
        return CustomerDto.fromModel(customer);
    }

    @Override
    public List<Customer> searchCustomer(String text) {
        return customerRepository.findByFirstNameContaining(text);
    }

    @Override
    public List<Customer> getCustomerWithoutOrder() {
        List<Customer> customerList = customerRepository.findAll();
        List<Customer> customersWithOrders = orderService.getOrders().stream().map(Order::getCustomer).collect(Collectors.toList());

        return customerList.stream().filter(customer -> !customersWithOrders.contains(customer)).collect(Collectors.toList());
    }
}
