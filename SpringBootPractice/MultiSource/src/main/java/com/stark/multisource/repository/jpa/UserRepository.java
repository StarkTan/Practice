package com.stark.multisource.repository.jpa;

import com.stark.multisource.entity.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
