package com.enoca.ibrahimciftci.repository;

import com.enoca.ibrahimciftci.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
