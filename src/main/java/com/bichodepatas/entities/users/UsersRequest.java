package com.bichodepatas.entities.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsersRequest(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank String telephone) {
}
