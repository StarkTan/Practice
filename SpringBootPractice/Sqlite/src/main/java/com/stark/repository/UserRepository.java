package com.stark.repository;

import com.stark.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component
public interface UserRepository extends CrudRepository<User, Long> {
}
