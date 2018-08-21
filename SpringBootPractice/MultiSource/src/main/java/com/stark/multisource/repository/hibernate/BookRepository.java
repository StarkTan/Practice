package com.stark.multisource.repository.hibernate;

import com.stark.multisource.entity.hibernate.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
