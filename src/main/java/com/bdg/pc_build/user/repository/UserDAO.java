package com.bdg.pc_build.user.repository;

import com.bdg.pc_build.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}