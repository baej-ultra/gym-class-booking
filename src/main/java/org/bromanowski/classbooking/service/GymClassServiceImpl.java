package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.entity.GymClass;
import org.bromanowski.classbooking.entity.Member;

import java.util.List;

public class GymClassServiceImpl implements GymClassService {

    @Override
    public List<GymClass> findAll() {
        return List.of();
    }

    @Override
    public GymClass findById(int id) {
        return null;
    }

    @Override
    public GymClass addMember(Member member) {
        return null;
    }

    @Override
    public GymClass editMember(int id, Member member) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
