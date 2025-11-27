package com.outreach.backend.controller;

import com.outreach.backend.dto.StudentEligibilityDTO;
import com.outreach.backend.entity.Placement;
import com.outreach.backend.service.ApplicationService;
import com.outreach.backend.service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/placements")
@CrossOrigin(origins = "http://localhost:5173") // Allow React app
public class PlacementController {

    @Autowired
    private PlacementService placementService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<Placement> getAllPlacements() {
        return placementService.getAllPlacements();
    }

    @GetMapping("/{id}/students")
    public List<StudentEligibilityDTO> getStudentsForPlacement(@PathVariable Integer id) {
        return placementService.getStudentsForPlacement(id);
    }

    @PostMapping("/{id}/students/{studentId}/select")
    public ResponseEntity<?> selectStudent(@PathVariable Integer id, @PathVariable Integer studentId) {
        boolean success = applicationService.selectStudent(id, studentId);
        if (success) {
            return ResponseEntity.ok("Student selected successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to select student");
        }
    }
}
