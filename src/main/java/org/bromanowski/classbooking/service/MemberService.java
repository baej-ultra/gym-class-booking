package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.entity.ScheduleEntry;
import org.bromanowski.classbooking.entity.Member;

import java.util.List;
import java.util.Set;

public interface MemberService {

    List<Member> findAll();

    Member findById(int id);

    Member addMember(Member member);

    Member editMember(int id, Member member);

    void deleteById(int id);


    Set<ScheduleEntry> getEvents(int id);
}
