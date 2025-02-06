package org.bromanowski.classbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "classes", schema = "class_booking")
public class GymClass {
    @Id
    @Column(name = "id_class", nullable = false)
    private Integer id;

    @Column(name = "class_name", length = 45)
    private String className;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}