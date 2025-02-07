package org.bromanowski.classbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "members", schema = "class_booking")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_member", nullable = false)
    private Integer id;

    @Min(value = 2, message = "First name has to be at least 2 characters")
    @Column(name = "first_name", length = 45)
    private String firstName;

    @Min(value = 2, message = "Last name has to be at least 2 characters")
    @Column(name = "last_name", length = 45)
    private String lastName;

    @Email(message = "Invalid email format. Please provide a valid email address.")
    @NotEmpty(message = "E-mail cannot be empty")
    @Column(name = "email", length = 45, unique = true)
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}