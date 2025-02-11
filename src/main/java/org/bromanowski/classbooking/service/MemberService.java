package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.model.MemberDto;
import org.bromanowski.classbooking.model.ScheduleEntry;

import java.util.List;

public interface MemberService {

    List<MemberDto> findAll();

    MemberDto findById(int id);

    MemberDto findByEmail(String email);

//    Member editMember(int id, Member member);

    List<ScheduleEntry> getEvents(int id, int week);

}
