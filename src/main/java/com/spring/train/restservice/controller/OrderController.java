package com.spring.train.restservice.controller;

import com.spring.train.restservice.config.OrderNotFoundException;
import com.spring.train.restservice.config.OrderResourceAssembler;
import com.spring.train.restservice.config.Status;
import com.spring.train.restservice.entity.Order;
import com.spring.train.restservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderResourceAssembler assembler;

    @GetMapping("/orders")
    public Resources<Resource<Order>> all() {

        List<Resource<Order>> orderList = orderRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(orderList,
                linkTo(methodOn(OrderController.class).all()).withSelfRel());

    }

    @GetMapping("/orders/{id}")
    public Resource<Order> one(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order repositoryOrder = order.get();
            return assembler.toResource(repositoryOrder);
        } else {
            throw new OrderNotFoundException(id);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Resource<Order>> newOrder(@RequestBody Order order)  {

        order.setStatus((Status.IN_PROGRESS));
        Order newOrder = orderRepository.save(order);

        return ResponseEntity
                .created(linkTo(methodOn(OrderController.class).one(newOrder.getId())).toUri())
                .body(assembler.toResource(newOrder));
    }

    @PutMapping("/orders/[id]/complete")
    public ResponseEntity<ResourceSupport> complete(@PathVariable Long id) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException((id)));
        if (order.getStatus() == Status.IN_PROGRESS)  {
            order.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toResource(orderRepository.save(order)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "can't complete an order that is in the " + order.getStatus() + " status"));
    }

    @DeleteMapping("/orders/[id]/cancel")
    public ResponseEntity<ResourceSupport> cancel(@PathVariable Long id) {

        Order order = orderRepository.findById(id).orElseThrow(() ->new OrderNotFoundException(id));

        if (order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.CANCELED);
            return ResponseEntity.ok(assembler.toResource(orderRepository.save(order)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't cancel an order that is in the " + order.getStatus() + " status"));

    }

}