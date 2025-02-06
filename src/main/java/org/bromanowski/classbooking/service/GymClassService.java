package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.entity.GymClass;
import org.bromanowski.classbooking.entity.Member;

import java.util.List;

public interface GymClassService {

    List<GymClass> findAll();

    GymClass findById(int id);

    GymClass addMember(Member member);

    GymClass editMember(int id, Member member);

    void deleteById(int id);
}
