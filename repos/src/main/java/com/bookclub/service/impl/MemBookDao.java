package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

import java.util.Arrays;
import java.util.List;

public class MemBookDao implements BookDao {
    private List<Book> books;

    public MemBookDao() {
        books = Arrays.asList(
                new Book("123", "Harry Potter", "A wizard story", 300, Arrays.asList("J.K. Rowling")),
                new Book("456", "Percy Jackson", "A demigod adventure", 350, Arrays.asList("Rick Riordan")),
                new Book("789", "Hunger Games", "Survival in an arena", 400, Arrays.asList("Suzanne Collins")));
    }

    @Override
    public List<Book> list() {
        return books;
    }

    @Override
    public Book find(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}
