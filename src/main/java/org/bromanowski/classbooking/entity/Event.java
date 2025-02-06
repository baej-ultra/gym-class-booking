package org.bromanowski.classbooking.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "schedule", schema = "class_booking")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_class", nullable = false)
    private Class idClass;

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

    public Class getIdClass() {
        return idClass;
    }

    public void setIdClass(Class idClass) {
        this.idClass = idClass;
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