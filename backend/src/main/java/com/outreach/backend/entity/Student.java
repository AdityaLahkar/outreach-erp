package com.outreach.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "Students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "roll_number", unique = true, nullable = false)
    private String rollNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private BigDecimal cgpa;

    @Column(name = "total_credits")
    private Integer totalCredits;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    @ManyToOne
    @JoinColumn(name = "specialisation_id")
    private Specialisation specialisation;

    @ManyToOne
    @JoinColumn(name = "placement_id")
    private Placement placement;
}
