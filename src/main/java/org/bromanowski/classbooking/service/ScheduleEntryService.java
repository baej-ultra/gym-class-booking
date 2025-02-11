package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.model.ScheduleEntry;
import org.bromanowski.classbooking.model.dto.ScheduleEntryDto;

import java.util.List;

public interface ScheduleEntryService {

    List<ScheduleEntry> getScheduleForCurrentWeek();

    List<ScheduleEntry> getScheduleForWeek(int week);

    List<ScheduleEntry> getScheduleForDate(String date);

    ScheduleEntry addScheduleEntry(ScheduleEntryDto scheduleEntryDto);
}
