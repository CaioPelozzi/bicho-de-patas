CREATE TABLE user_roles(
    users_id BIGINT,
    roles_id BIGINT,

    PRIMARY KEY (users_id, roles_id),
    FOREIGN KEY (users_id) REFERENCES users(id),
    FOREIGN KEY (roles_id) REFERENCES roles(id)
)