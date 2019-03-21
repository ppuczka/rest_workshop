package com.spring.train.restservice.config;

public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException(Long id)  {
        super("could not find employee " + id);
    }

}
