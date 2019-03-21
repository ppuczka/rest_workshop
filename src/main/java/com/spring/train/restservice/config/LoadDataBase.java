package com.spring.train.restservice.config;

import com.spring.train.restservice.entity.Employee;
import com.spring.train.restservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
@AllArgsConstructor
public class LoadDataBase {

    public List<Employee> populateEmployee() {
        List<Employee> employee = new ArrayList<>();
        employee.add(new Employee(1L, "John", "contractor"));
        employee.add(new Employee(2L, "Megan", "dev"));
        return employee;
    }
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args ->  {log.info("created {}", repository.saveAll(populateEmployee()));
        };
    }
}
