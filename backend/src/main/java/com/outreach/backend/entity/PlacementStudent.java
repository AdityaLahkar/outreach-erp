package com.outreach.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "Placement_Student")
@Data
public class PlacementStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "placement_id")
    private Placement placement;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "cv_application")
    private String cvApplication;

    private String about;

    private String acceptance; // 'APPLIED', 'SELECTED', 'REJECTED'

    private LocalDateTime date;
}
