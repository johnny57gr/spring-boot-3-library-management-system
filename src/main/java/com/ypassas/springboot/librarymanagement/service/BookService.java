package com.ypassas.springboot.librarymanagement.service;

import com.ypassas.springboot.librarymanagement.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    List<Book> findByIsAvailableTrue();

    Book findById(int theId);

    Book save(Book theBook);

    void deleteById(int theId);
}
