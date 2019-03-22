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
    private String firstName;

    @Column("lastname")
    private String lastName;

    @Column("rol")
    private String role;

    public Employee(Long id, String firstName, String role) {
        this.id = id;
        this.firstName = firstName;
        this.role = role;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }


}
