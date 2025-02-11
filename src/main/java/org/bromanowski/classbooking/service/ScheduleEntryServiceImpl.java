package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.model.ScheduleEntry;
import org.bromanowski.classbooking.model.dto.ScheduleEntryDto;
import org.bromanowski.classbooking.model.mapper.ScheduleEntryMapper;
import org.bromanowski.classbooking.repository.ScheduleEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Service
public class ScheduleEntryServiceImpl implements ScheduleEntryService {

    private final ScheduleEntryRepository scheduleEntryRepository;
    private final ScheduleEntryMapper scheduleEntryMapper;

    @Autowired
    public ScheduleEntryServiceImpl(ScheduleEntryRepository scheduleEntryRepository, ScheduleEntryMapper scheduleEntryMapper) {
        this.scheduleEntryRepository = scheduleEntryRepository;
        this.scheduleEntryMapper = scheduleEntryMapper;
    }

    @Override
    public List<ScheduleEntry> getScheduleForCurrentWeek() {
        LocalDate localDate = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int week = localDate.get(weekFields.weekOfYear());
        return scheduleEntryRepository.findAllByWeek(week);
    }

    @Override
    public List<ScheduleEntry> getScheduleForWeek(int week) {
        return scheduleEntryRepository.findAllByWeek(week);
    }

    @Override
    public List<ScheduleEntry> getScheduleForDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return scheduleEntryRepository.findAllByDay(localDate);
    }

    @Override
    public ScheduleEntry addScheduleEntry(ScheduleEntryDto scheduleEntryDto) {
        ScheduleEntry se = scheduleEntryMapper.mapToEntity(scheduleEntryDto);
        return scheduleEntryRepository.save(se);
    }
}
