package com.spring.train.restservice.controller;

import com.spring.train.restservice.config.OrderResourceAssembler;
import com.spring.train.restservice.entity.Order;
import com.spring.train.restservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderResourceAssembler assembler;

    @GetMapping("/orders")
    Resources<Resource<Order>> all() {

        List<Resource<Order>> orderList = orderRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(orderList,
                linkTo(methodOn(OrderController.class).all()).withSelfRel());

    }

}