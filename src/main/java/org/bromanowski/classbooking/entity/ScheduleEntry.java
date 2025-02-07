package org.bromanowski.classbooking.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "schedule", schema = "class_booking")
public class ScheduleEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_class", nullable = false)
    private GymClass idGymClass;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instructor")
    private Instructor idInstructor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GymClass getIdClass() {
        return idGymClass;
    }

    public void setIdClass(GymClass idGymClass) {
        this.idGymClass = idGymClass;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Instructor getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Instructor idInstructor) {
        this.idInstructor = idInstructor;
    }

}