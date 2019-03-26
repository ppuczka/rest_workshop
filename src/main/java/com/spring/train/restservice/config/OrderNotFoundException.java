package com.spring.train.restservice.config;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("Order not found in database" + id);
    }
}
