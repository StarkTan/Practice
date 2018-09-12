package com.stark.jpa.repository;

import com.stark.jpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testExample() throws Exception {

        User user = new User();
        user.setAge(123);
        user.setName("123");

        User save = repository.save(user);
        System.out.println(repository.test());
        assertNotNull(save.getId());
    }
}