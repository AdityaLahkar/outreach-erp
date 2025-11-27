package com.outreach.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Domains")
@Data
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domain_id")
    private Integer domainId;

    private String program;
    private String batch;
    private Integer capacity;
    private String qualification;
}
