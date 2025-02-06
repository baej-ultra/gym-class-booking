package org.bromanowski.classbooking.repository;

import org.bromanowski.classbooking.entity.GymClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymClassRepository extends JpaRepository<GymClass, Integer> {
}
