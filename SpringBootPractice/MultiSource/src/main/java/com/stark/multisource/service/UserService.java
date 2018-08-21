package com.stark.multisource.service;

import com.stark.multisource.entity.jpa.User;
import com.stark.multisource.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public void add(User user) {
        repository.save(user);
    }

    public void remove(User user) {
        repository.delete(user);
    }

    public User get(long id) {
        return repository.findById(id).orElse(null);
    }
}
