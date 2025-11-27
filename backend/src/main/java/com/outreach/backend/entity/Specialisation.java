package com.outreach.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Specialisation")
@Data
public class Specialisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialisation_id")
    private Integer specialisationId;

    @Column(unique = true, nullable = false)
    private String code;

    private String name;
    private String description;
    private Integer year;

    @Column(name = "credits_required")
    private Integer creditsRequired;
}
