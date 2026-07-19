package com.bichodepatas.entities.roles;

import jakarta.validation.constraints.NotBlank;

public record RolesRequest(@NotBlank String name) {
}
