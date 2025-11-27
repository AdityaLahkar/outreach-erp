package com.outreach.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Departments")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer departmentId;

    @Column(nullable = false)
    private String name;

    private Integer capacity;
}
