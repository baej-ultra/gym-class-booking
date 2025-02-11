package org.bromanowski.classbooking.rest;

import jakarta.validation.Valid;
import org.bromanowski.classbooking.model.ScheduleEntry;
import org.bromanowski.classbooking.model.dto.ScheduleEntryDto;
import org.bromanowski.classbooking.service.ScheduleEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleEntryController {

    private final ScheduleEntryService scheduleEntryService;

    @Autowired
    public ScheduleEntryController(ScheduleEntryService scheduleEntryService) {
        this.scheduleEntryService = scheduleEntryService;
    }

    @GetMapping()
    ResponseEntity<List<ScheduleEntry>> getScheduleForWeek() {
        return ResponseEntity.ok(scheduleEntryService.getScheduleForCurrentWeek());
    }

    @GetMapping("/day/{date}")
    ResponseEntity<List<ScheduleEntry>> getScheduleForDate(@PathVariable String date) {
        return ResponseEntity.ok(scheduleEntryService.getScheduleForDate(date));
    }

    @GetMapping("/week")
    ResponseEntity<List<ScheduleEntry>> getScheduleForWeek(@RequestParam(value = "n", required = false) Integer week) {
        if (week == null) {
            return ResponseEntity.ok(scheduleEntryService.getScheduleForCurrentWeek());
        } else {
            return ResponseEntity.ok(scheduleEntryService.getScheduleForWeek(week));
        }
    }

    @PostMapping
    ResponseEntity<ScheduleEntry> addEntry(@RequestBody @Valid ScheduleEntryDto scheduleEntryDto) {
        ScheduleEntry newScheduleEntry = scheduleEntryService.addScheduleEntry(scheduleEntryDto);
        return ResponseEntity.ok(newScheduleEntry);
    }
}
