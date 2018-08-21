package com.stark.multisource.service;

import com.stark.multisource.Application;
import com.stark.multisource.entity.hibernate.Book;
import com.stark.multisource.entity.jpa.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Test
    public void test() {
        User user1 = new User(1L, "name1", 10, true);
        User user2 = new User(2L, "name2", 20, false);
        userService.add(user1);
        userService.add(user2);
        assertEquals(userService.findAll().size(), 2);
        assertEquals(userService.get(1L).getName(), user1.getName());
        assertEquals(userService.get(2L).getName(), user2.getName());
        assertNull(userService.get(3L));
        userService.remove(user1);
        userService.remove(user2);
        assertEquals(userService.findAll().size(), 0);

        Book book1 = new Book(1L, "name1", 10, "author1");
        Book book2 = new Book(2L, "name2", 20, "author2");
        bookService.add(book1);
        bookService.add(book2);
        assertEquals(bookService.findAll().size(), 2);
        assertEquals(bookService.get(1L).getName(), user1.getName());
        assertEquals(bookService.get(2L).getName(), user2.getName());
        assertNull(bookService.get(3L));
        bookService.remove(book1);
        bookService.remove(book2);
        assertEquals(bookService.findAll().size(), 0);
    }

}