package com.enoca.ibrahimciftci.repository;

import com.enoca.ibrahimciftci.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByFirstNameContaining(String text);

}
