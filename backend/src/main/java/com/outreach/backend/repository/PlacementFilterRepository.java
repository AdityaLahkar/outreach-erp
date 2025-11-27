package com.outreach.backend.repository;

import com.outreach.backend.entity.PlacementFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlacementFilterRepository extends JpaRepository<PlacementFilter, Integer> {
    List<PlacementFilter> findByPlacementId(Integer placementId);
}
