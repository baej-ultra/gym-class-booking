package org.bromanowski.classbooking.service;

import jakarta.persistence.EntityNotFoundException;
import org.bromanowski.classbooking.model.Instructor;
import org.bromanowski.classbooking.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(int id) {
        return instructorRepository.findById(id).orElseThrow( () ->
                new EntityNotFoundException("Instructor not found for id " + id));
    }

    @Override
    public Instructor addInstructor(Instructor instructor) {
        instructor.setId(null);
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor editInstructor(int id, Instructor instructor) {
        checkIfExistsById(id);
        instructor.setId(id);
        instructorRepository.save(instructor);
        return null;
    }

    @Override
    public void deleteById(int id) {
        checkIfExistsById(id);
        instructorRepository.deleteById(id);
    }

    private void checkIfExistsById(int id) {
        if (!instructorRepository.existsById(id)) {
            throw new EntityNotFoundException("Instructor not found for id " + id);
        }
    }
}
