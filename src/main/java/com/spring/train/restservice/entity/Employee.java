package com.spring.train.restservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @PrimaryKey
    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @Column("rol")
    private String role;


}
