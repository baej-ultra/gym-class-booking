package org.bromanowski.classbooking.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserDto(
        @NotNull(message = "Cannot be null")
        @Size(min = 2, max = 100)
        String username,

        @NotNull
        @Size(max = 255)
        String password,

        @NotNull
        String firstName,

        @NotNull
        String lastName,

        @NotNull
        String email) {
}
