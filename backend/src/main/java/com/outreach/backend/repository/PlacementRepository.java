package com.outreach.backend.repository;

import com.outreach.backend.entity.Placement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacementRepository extends JpaRepository<Placement, Integer> {
}
