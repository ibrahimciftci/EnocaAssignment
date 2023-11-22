package com.enoca.ibrahimciftci.dto;

import com.enoca.ibrahimciftci.model.Customer;
import com.enoca.ibrahimciftci.model.Order;

import java.util.Set;

public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private Set<Order> orders;

    public CustomerDto(int id, String firstName, String lastName, int age, Set<Order> orders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.orders = orders;
    }

    public CustomerDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", orders=" + orders +
                '}';
    }

    public static CustomerDto fromModel(Customer customer){
        return new CustomerDto(customer.getId(),customer.getFirstName(),customer.getLastName(),customer.getAge(),customer.getOrders());
    }
}
