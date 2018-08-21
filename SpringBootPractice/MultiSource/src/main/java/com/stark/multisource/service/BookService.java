package com.stark.multisource.service;

import com.stark.multisource.entity.hibernate.Book;
import com.stark.multisource.entity.jpa.User;
import com.stark.multisource.repository.hibernate.BookRepository;
import com.stark.multisource.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> findAll() {
        return repository.findAll();
    }

    public void add(Book book) {
        repository.save(book);
    }

    public void remove(Book book) {
        repository.delete(book);
    }

    public Book get(long id) {
        return repository.findById(id).orElse(null);
    }
}
