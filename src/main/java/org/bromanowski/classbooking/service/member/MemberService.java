package org.bromanowski.classbooking.service.member;

import org.bromanowski.classbooking.model.dto.MemberDto;
import org.bromanowski.classbooking.model.ScheduleEntry;

import java.util.List;

public interface MemberService {

    List<MemberDto> findAll();

    MemberDto findById(int id);

    MemberDto findByEmail(String email);

//    Member editMember(int id, Member member);

    List<ScheduleEntry> getEventsByWeek(int id, int week);

    List<ScheduleEntry> getAllEvents(int id);

}
