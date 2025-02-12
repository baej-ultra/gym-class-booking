package org.bromanowski.classbooking.model.dto;

import java.time.Instant;

public record ScheduleEntryDto(
        Integer gymClassId,
        Instant startTime,
        Integer duration,
        Integer instructorId) {
}
