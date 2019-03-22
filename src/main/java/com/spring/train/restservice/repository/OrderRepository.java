package com.spring.train.restservice.repository;

import com.spring.train.restservice.entity.Order;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CassandraRepository<Order, Long> {

}
