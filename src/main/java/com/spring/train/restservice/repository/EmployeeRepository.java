package com.spring.train.restservice.repository;

import com.spring.train.restservice.entity.Employee;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CassandraRepository<Employee, Long> {

    Employee readFirstBy(Long id);
}
