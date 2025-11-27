package com.outreach.backend.repository;

import com.outreach.backend.entity.PlacementStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface PlacementStudentRepository extends JpaRepository<PlacementStudent, Integer> {
    Optional<PlacementStudent> findByPlacementIdAndStudentStudentId(Integer placementId, Integer studentId);

    List<PlacementStudent> findByPlacementId(Integer placementId);
}
