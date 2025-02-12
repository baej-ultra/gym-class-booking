package org.bromanowski.classbooking.service.gymclass;

import org.bromanowski.classbooking.model.GymClass;

import java.util.List;

public interface GymClassService {

    List<GymClass> findAll();

    GymClass findById(int id);

    GymClass findByClassName(String gymClassname);

    GymClass addGymClass(GymClass gymClass);

    GymClass editGymClass(int id, GymClass gymClass);

    void deleteById(int id);
}
