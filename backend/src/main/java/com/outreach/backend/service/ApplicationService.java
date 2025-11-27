package com.outreach.backend.service;

import com.outreach.backend.entity.Placement;
import com.outreach.backend.entity.PlacementStudent;
import com.outreach.backend.entity.Student;
import com.outreach.backend.repository.PlacementRepository;
import com.outreach.backend.repository.PlacementStudentRepository;
import com.outreach.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private PlacementStudentRepository placementStudentRepository;

    @Autowired
    private PlacementRepository placementRepository;

    @Autowired
    private StudentRepository studentRepository;

    public boolean selectStudent(Integer placementId, Integer studentId) {
        Optional<PlacementStudent> existingApp = placementStudentRepository
                .findByPlacementIdAndStudentStudentId(placementId, studentId);

        if (existingApp.isPresent()) {
            PlacementStudent app = existingApp.get();
            if ("SELECTED".equals(app.getAcceptance())) {
                app.setAcceptance("APPLIED"); // Deselect
            } else {
                app.setAcceptance("SELECTED"); // Select
            }
            placementStudentRepository.save(app);
            return true;
        } else {
            // Create new application if not exists (Direct Selection)
            Placement placement = placementRepository.findById(placementId)
                    .orElseThrow(() -> new RuntimeException("Placement not found"));
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            PlacementStudent newApp = new PlacementStudent();
            newApp.setPlacement(placement);
            newApp.setStudent(student);
            newApp.setAcceptance("SELECTED");
            newApp.setDate(LocalDateTime.now());
            placementStudentRepository.save(newApp);
            return true;
        }
    }
}
