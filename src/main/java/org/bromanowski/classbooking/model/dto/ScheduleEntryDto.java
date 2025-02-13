package org.bromanowski.classbooking.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record ScheduleEntryDto(
        @NotNull
        Integer gymClassId,

        Instant startTime,

        @NotNull
        @Min(1)
        Integer duration,

        @NotNull
        Integer instructorId,

        @NotNull
        @Min(1)
        Integer capacity) {
}
