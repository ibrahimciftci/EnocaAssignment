package com.enoca.ibrahimciftci.service;

import com.enoca.ibrahimciftci.dto.CustomerDto;
import com.enoca.ibrahimciftci.model.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getCustomers();

    Customer findById(int id);

    CustomerDto saveCustomer(CustomerDto customerDto);

    boolean deleteCustomer(int id);

    CustomerDto updateCustomer(int id, CustomerDto customerDto);
}
