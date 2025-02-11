package org.bromanowski.classbooking.model.mapper;

import org.bromanowski.classbooking.model.GymClass;
import org.bromanowski.classbooking.model.Instructor;
import org.bromanowski.classbooking.model.ScheduleEntry;
import org.bromanowski.classbooking.model.dto.ScheduleEntryDto;
import org.bromanowski.classbooking.service.GymClassService;
import org.bromanowski.classbooking.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleEntryMapper {

    private final GymClassService gymClassService;
    private final InstructorService instructorService;

    @Autowired
    public ScheduleEntryMapper(GymClassService gymClassService, InstructorService instructorService) {
        this.gymClassService = gymClassService;
        this.instructorService = instructorService;
    }

    public ScheduleEntry mapToEntity(ScheduleEntryDto scheduleEntryDTO){

        GymClass gymClass = gymClassService.findById(scheduleEntryDTO.gymClassId());
        Instructor instructor = instructorService.findById(scheduleEntryDTO.instructorId());

        ScheduleEntry se = new ScheduleEntry();
        se.setGymClass(gymClass);
        se.setInstructor(instructor);
        se.setDuration(scheduleEntryDTO.duration());
        se.setStartTime(scheduleEntryDTO.startTime());
        se.setId(null);

        return se;
    }
}
