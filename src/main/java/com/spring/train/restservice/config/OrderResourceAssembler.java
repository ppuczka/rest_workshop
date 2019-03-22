package com.spring.train.restservice.config;

import com.spring.train.restservice.controller.OrderController;
import com.spring.train.restservice.entity.Order;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;

import static org.springframework.hateoas.jaxrs.JaxRsLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class OrderResourceAssembler implements ResourceAssembler<Order, Resource<Order>> {

    @Override
    public Resource<Order> toResource(Order order) {
        return new Resource<>(order,

                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("orders"));
    }
}
