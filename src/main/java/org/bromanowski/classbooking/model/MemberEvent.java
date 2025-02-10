package org.bromanowski.classbooking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "member_event", schema = "class_booking")
public class MemberEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_memberevent", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_member", nullable = false)
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_event", nullable = false)
    private ScheduleEntry scheduleEntry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member idMember) {
        this.member = idMember;
    }

    public ScheduleEntry getIdEvent() {
        return scheduleEntry;
    }

    public void setIdEvent(ScheduleEntry idScheduleEntry) {
        this.scheduleEntry = idScheduleEntry;
    }

}