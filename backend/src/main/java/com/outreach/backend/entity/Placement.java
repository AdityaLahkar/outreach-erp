package com.outreach.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "Placement")
@Data
public class Placement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    private String profile;
    private String description;
    private Integer intake;

    @Column(name = "minimum_grade")
    private BigDecimal minimumGrade;

    @OneToMany(mappedBy = "placement", fetch = FetchType.EAGER)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("placement")
    private java.util.List<PlacementFilter> filters;
}
