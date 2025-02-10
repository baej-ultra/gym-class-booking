package org.bromanowski.classbooking.repository;

import org.bromanowski.classbooking.model.ScheduleEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleEntryRepository extends JpaRepository<ScheduleEntry, Integer> {

    @Query("SELECT se FROM ScheduleEntry se WHERE WEEK(se.startTime) = :week")
    List<ScheduleEntry> findAllByWeek(@Param("week") int week);

    @Query("SELECT se FROM ScheduleEntry se WHERE FUNCTION('DATE', se.startTime) = :date")
    List<ScheduleEntry> findAllByDay(@Param("date") LocalDate date);

    boolean existsByStartTime(Instant startTime);
}
