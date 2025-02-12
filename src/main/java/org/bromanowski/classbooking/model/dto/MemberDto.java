package org.bromanowski.classbooking.model.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public record MemberDto(

        @Length(min = 2)
        String firstName,

        @Length(min = 2)
        String lastName,

        @Email
        String email) {
}
