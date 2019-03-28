package com.spring.train.restservice.entity;

import com.spring.train.restservice.config.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("Customer_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Column
    @Id
    @PrimaryKey
    private Long id;

    @Column
    private String description;

    private Status status;
}
