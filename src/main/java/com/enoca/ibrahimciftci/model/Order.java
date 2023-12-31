package com.enoca.ibrahimciftci.model;

import com.enoca.ibrahimciftci.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "'order'")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;


    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order(Date createDate, double totalPrice) {
        this.createDate = createDate;
        this.totalPrice = totalPrice;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", totalPrice=" + totalPrice +
                ", customer=" + customer +
                '}';
    }

    public static Order fromDto(OrderDto orderDto){
        Order order = new Order();
        Date createDate=new Date();

        try {
            createDate = new SimpleDateFormat("yyyy-MM-dd").parse(orderDto.getCreateDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setCustomer(orderDto.getCustomer());
        order.setId(orderDto.getId());
        order.setCreateDate(createDate);
        order.setTotalPrice(orderDto.getTotalPrice());

        order.setCustomer(orderDto.getCustomer());
        return order;
    }
}
