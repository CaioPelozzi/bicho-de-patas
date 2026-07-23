package com.bichodepatas.entities.users;

public record UsersResponse(
        Long id,
        String name,
        String email,
        String telephone
) {
}
