package com.bookclub.dao.impl;

import com.bookclub.model.Book;
import java.util.List;

public interface BookDao {
    // This method returns a list of all books
    List<Book> list();

    // This method finds a book by its ISBN
    Book find(String isbn);

    // This method checks if a book exists based on ISBN
    boolean exists(String isbn);

    // This method adds a book to the collection
    void add(Book book);

    // This method updates an existing book
    void update(Book book);
}
