package org.bromanowski.classbooking.repository;

import org.bromanowski.classbooking.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    void removeInstructorById(Integer id);
}


