package org.bromanowski.classbooking.repository;

import org.bromanowski.classbooking.entity.GymClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymClassRepository extends JpaRepository<GymClass, Integer> {

    Optional<GymClass> findByClassName(String className);
}
