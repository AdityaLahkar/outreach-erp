package com.outreach.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Organisations")
@Data
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String address;
}
