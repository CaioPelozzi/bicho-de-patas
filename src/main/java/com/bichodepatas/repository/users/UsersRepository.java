package com.bichodepatas.repository.users;

import com.bichodepatas.entities.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
