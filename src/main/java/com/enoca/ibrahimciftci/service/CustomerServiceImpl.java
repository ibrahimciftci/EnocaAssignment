package com.enoca.ibrahimciftci.service;

import com.enoca.ibrahimciftci.dto.CustomerDto;
import com.enoca.ibrahimciftci.model.Customer;
import com.enoca.ibrahimciftci.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
}
