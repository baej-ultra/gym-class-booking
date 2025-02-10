package org.bromanowski.classbooking.model;

import java.time.Instant;

public record ScheduleEntryDto(Integer id,
                               Integer gymClassId,
                               Instant startTime,
                               Integer duration,
                               Integer instructorId) {
}
