package com.javainuse.bootecommerce.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javainuse.bootecommerce.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
