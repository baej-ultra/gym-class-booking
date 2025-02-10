package org.bromanowski.classbooking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "instructors", schema = "class_booking")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instructor", nullable = false)
    private Integer id;

    @Column(name = "full_name", length = 45)
    private String fullName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}