package org.bromanowski.classbooking.repository;

import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.ScheduleEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findMemberByEmail(String email);

    boolean existsByEmail(String email);

    @Query("""
            SELECT se FROM ScheduleEntry se
            JOIN MemberEvent me ON se.id = me.scheduleEntry.id
            WHERE me.member.id = :memberId
            AND WEEK(se.startTime) = :week""")
    List<ScheduleEntry> findEventsByWeekForMember(@Param("memberId") int memberId,
                                                  @Param("week") int week);

}
