package com.outreach.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String title;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
