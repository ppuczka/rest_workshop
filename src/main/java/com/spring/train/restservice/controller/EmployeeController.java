package com.spring.train.restservice.controller;

import com.spring.train.restservice.config.EmployeeNotFoundException;
import com.spring.train.restservice.config.EmployeeResourceAssembler;
import com.spring.train.restservice.entity.Employee;
import com.spring.train.restservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@Slf4j
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeResourceAssembler assembler;

    @GetMapping("/employees") public Resources<Resource<Employee>> all() {
        List<Resource<Employee>> employees = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());

    }

    @PostMapping("/employees")
    Employee newEmloyee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);

    }

    @GetMapping("/employees/{id}") public Resource<Employee> one(@PathVariable Long id) {
        Optional<Employee> one = (repository.findById(id));
        if (one.isPresent()) {
            Employee findById = one.get();
            return assembler.toResource(findById);
        } else {
            throw new EmployeeNotFoundException(id);
        }

    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });

    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
