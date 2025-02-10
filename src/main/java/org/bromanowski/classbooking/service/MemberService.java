package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.ScheduleEntry;

import java.util.List;

public interface MemberService {

    List<Member> findAll();

    Member findById(int id);

    Member findByEmail(String email);

    Member addMember(Member member);

    Member editMember(int id, Member member);

    void deleteById(int id);

    List<ScheduleEntry> getEvents(int id, int week);

}
