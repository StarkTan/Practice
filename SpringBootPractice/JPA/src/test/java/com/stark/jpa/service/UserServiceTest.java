package com.stark.jpa.service;

import com.stark.jpa.Application;
import com.stark.jpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void test() {
        User user1 = new User(1L, "name1", 10, true);
        User user2 = new User(2L, "name2", 20, false);
        service.add(user1);
        service.add(user2);
        assertEquals(service.findAll().size(), 2);
        assertEquals(service.get(1L).getName(), user1.getName());
        assertEquals(service.get(2L).getName(), user2.getName());
        assertNull(service.get(3L));
        service.remove(user1);
        service.remove(user2);
        assertEquals(service.findAll().size(), 0);
    }

}