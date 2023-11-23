package com.enoca.ibrahimciftci.service;

import com.enoca.ibrahimciftci.model.Order;

import java.util.Date;
import java.util.List;

public interface DateService {

    List<Order> afterOrders(Date date);
}
