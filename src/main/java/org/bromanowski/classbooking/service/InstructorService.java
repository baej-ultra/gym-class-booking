package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.model.Instructor;

import java.util.List;

public interface InstructorService {

    List<Instructor> findAll();

    Instructor findById(int id);

    Instructor addInstructor(Instructor Instructor);

    Instructor editInstructor(int id, Instructor Instructor);

    void deleteById(int id);
}
