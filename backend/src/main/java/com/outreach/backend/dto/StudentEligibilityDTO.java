package com.outreach.backend.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class StudentEligibilityDTO {
    private Integer studentId;
    private String rollNumber;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal cgpa;
    private String domain;
    private String specialisation;
    private boolean isEligible;
    private String eligibilityReason; // Optional: why they are/aren't eligible
    private String applicationStatus; // 'APPLIED', 'SELECTED', 'REJECTED', or null
}
