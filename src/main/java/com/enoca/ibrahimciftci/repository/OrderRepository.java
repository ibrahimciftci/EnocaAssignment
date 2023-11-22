package com.enoca.ibrahimciftci.repository;

import com.enoca.ibrahimciftci.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
