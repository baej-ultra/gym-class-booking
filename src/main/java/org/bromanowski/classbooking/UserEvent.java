package org.bromanowski.classbooking;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.bromanowski.classbooking.model.ScheduleEntry;
import org.bromanowski.classbooking.model.User;

@Entity
@Table(name = "user_event")
public class UserEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_userevent", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @NotNull
    @ManyToOne( optional = false)
    @JoinColumn(name = "id_event", nullable = false)
    private ScheduleEntry event;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ScheduleEntry getEvent() {
        return event;
    }

    public void setEvent(ScheduleEntry event) {
        this.event = event;
    }

}