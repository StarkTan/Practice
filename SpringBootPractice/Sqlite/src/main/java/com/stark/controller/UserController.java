package com.stark.controller;

import com.stark.entity.User;
import com.stark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/add")
    public User add(String name){
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }
    @RequestMapping("/list")
    public Iterable<User> list(){
        Iterable<User> all = userRepository.findAll();
        return all;
    }
}