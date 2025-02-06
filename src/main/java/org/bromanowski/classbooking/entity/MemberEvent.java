package org.bromanowski.classbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "member_event", schema = "class_booking")
public class MemberEvent {
    @Id
    @Column(name = "id_membereven", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_member", nullable = false)
    private Member idMember;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_event", nullable = false)
    private ScheduleEntry idScheduleEntry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Member getIdMember() {
        return idMember;
    }

    public void setIdMember(Member idMember) {
        this.idMember = idMember;
    }

    public ScheduleEntry getIdEvent() {
        return idScheduleEntry;
    }

    public void setIdEvent(ScheduleEntry idScheduleEntry) {
        this.idScheduleEntry = idScheduleEntry;
    }

}