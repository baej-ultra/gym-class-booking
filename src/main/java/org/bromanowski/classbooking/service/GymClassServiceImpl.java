package org.bromanowski.classbooking.service;

import jakarta.persistence.EntityNotFoundException;
import org.bromanowski.classbooking.model.GymClass;
import org.bromanowski.classbooking.repository.GymClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymClassServiceImpl implements GymClassService {

    private final GymClassRepository gymClassRepository;
    
    @Autowired
    public GymClassServiceImpl(GymClassRepository gymClassRepository) {
        this.gymClassRepository = gymClassRepository;
    }

    @Override
    public List<GymClass> findAll() {
        return gymClassRepository.findAll();
    }

    @Override
    public GymClass findById(int id) {
        var gymClassOptional = gymClassRepository.findById(id);
        return gymClassOptional.orElseThrow(() ->
                new EntityNotFoundException("No class found for id " + id));
    }

    @Override
    public GymClass findByClassName(String gymClassname) {
        var gymClassOptional = gymClassRepository.findByClassName(gymClassname);
        return gymClassOptional.orElseThrow(() ->
                new EntityNotFoundException("No class found for id " + gymClassname));
    }

    @Override
    public GymClass addGymClass(GymClass gymClass) {
        gymClass.setId(null);
        return gymClassRepository.save(gymClass);
    }

    @Override
    public GymClass editGymClass(int id, GymClass gymClass) {
        checkIfExistsById(id);
        gymClass.setId(id);
        return gymClassRepository.save(gymClass);
    }

    @Override
    public void deleteById(int id) {
        checkIfExistsById(id);
        gymClassRepository.deleteById(id);
    }

    private void checkIfExistsById(int id) {
        if (!gymClassRepository.existsById(id)) {
            throw new EntityNotFoundException("No class found for id " + id);
        }
    }
}

