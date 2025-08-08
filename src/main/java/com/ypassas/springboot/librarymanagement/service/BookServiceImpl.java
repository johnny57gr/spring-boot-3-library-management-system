package com.ypassas.springboot.librarymanagement.service;

import com.ypassas.springboot.librarymanagement.model.Book;
import com.ypassas.springboot.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository) {
        bookRepository = theBookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByIsAvailableTrue() {
        return bookRepository.findByIsAvailableTrue();
    }

    @Override
    public Book findById(int theId) {
        Optional<Book> result = bookRepository.findById(theId);

        Book theBook = null;

        if (result.isPresent()) {
            theBook = result.get();
        }
        else {
            // we didn't find the book
            throw new RuntimeException("Did not find book id - " + theId);
        }

        return theBook;
    }

    @Override
    public Book save(Book theBook) {
        boolean isNew = (theBook.getId() == null);

        // check if isbn exists
        Optional<Book> byIsbn = bookRepository.findByIsbn(theBook.getIsbn());
        if (byIsbn.isPresent() && (isNew || byIsbn.get().getId() != theBook.getId())) {
            throw new IllegalArgumentException("The book already exists!");
        }

        return bookRepository.save(theBook);
    }

    @Override
    public void deleteById(int theId) {
        bookRepository.deleteById(theId);
    }
}
