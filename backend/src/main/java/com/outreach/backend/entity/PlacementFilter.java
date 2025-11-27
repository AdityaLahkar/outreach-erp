package com.outreach.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Placement_Filter")
@Data
public class PlacementFilter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "placement_id")
    private Placement placement;

    @ManyToOne
    @JoinColumn(name = "specialisation_id")
    private Specialisation specialisation;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;
}
