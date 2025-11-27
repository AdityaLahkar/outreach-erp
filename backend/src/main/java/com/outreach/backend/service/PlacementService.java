package com.outreach.backend.service;

import com.outreach.backend.dto.StudentEligibilityDTO;
import com.outreach.backend.entity.*;
import com.outreach.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlacementService {

    @Autowired
    private PlacementRepository placementRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PlacementFilterRepository placementFilterRepository;

    @Autowired
    private PlacementStudentRepository placementStudentRepository;

    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }

    public List<StudentEligibilityDTO> getStudentsForPlacement(Integer placementId) {
        Placement placement = placementRepository.findById(placementId)
                .orElseThrow(() -> new RuntimeException("Placement not found"));

        List<PlacementFilter> filters = placementFilterRepository.findByPlacementId(placementId);
        List<Student> allStudents = studentRepository.findAll();
        List<PlacementStudent> applications = placementStudentRepository.findByPlacementId(placementId);

        // Map studentId to Application for quick lookup
        Map<Integer, PlacementStudent> applicationMap = applications.stream()
                .collect(Collectors.toMap(app -> app.getStudent().getStudentId(), app -> app));

        List<StudentEligibilityDTO> result = new ArrayList<>();

        for (Student student : allStudents) {
            StudentEligibilityDTO dto = new StudentEligibilityDTO();
            dto.setStudentId(student.getStudentId());
            dto.setRollNumber(student.getRollNumber());
            dto.setFirstName(student.getFirstName());
            dto.setLastName(student.getLastName());
            dto.setEmail(student.getEmail());
            dto.setCgpa(student.getCgpa());
            dto.setDomain(student.getDomain() != null ? student.getDomain().getProgram() : "");
            dto.setSpecialisation(student.getSpecialisation() != null ? student.getSpecialisation().getCode() : "");

            // Check Application Status
            if (applicationMap.containsKey(student.getStudentId())) {
                dto.setApplicationStatus(applicationMap.get(student.getStudentId()).getAcceptance());
            }

            // Check Eligibility
            boolean isEligible = true;
            StringBuilder reason = new StringBuilder();

            // 1. Grade Check
            if (student.getCgpa() == null || student.getCgpa().compareTo(placement.getMinimumGrade()) < 0) {
                isEligible = false;
                reason.append("CGPA too low. ");
            }

            // 2. Domain/Spec Check (Only if filters exist)
            if (!filters.isEmpty()) {
                boolean matchFound = false;
                for (PlacementFilter filter : filters) {
                    boolean domainMatch = filter.getDomain().getDomainId().equals(student.getDomain().getDomainId());
                    boolean specMatch = filter.getSpecialisation().getSpecialisationId()
                            .equals(student.getSpecialisation().getSpecialisationId());
                    if (domainMatch && specMatch) {
                        matchFound = true;
                        break;
                    }
                }
                if (!matchFound) {
                    isEligible = false;
                    reason.append("Domain/Specialisation mismatch. ");
                }
            }

            dto.setEligible(isEligible);
            dto.setEligibilityReason(reason.toString());
            result.add(dto);
        }

        return result;
    }
}
