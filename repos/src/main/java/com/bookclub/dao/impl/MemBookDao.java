package com.bookclub.dao.impl;

import com.bookclub.model.Book;
import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;

@Repository
public class MemBookDao implements BookDao {

    private List<Book> books;

    public MemBookDao() {
        books = Arrays.asList(
                new Book("123", "Harry Potter", "A wizard story", 300, null),
                new Book("456", "Percy Jackson", "A demigod adventure", 320, null),
                new Book("789", "Hunger Games", "Survival in an arena", 400, null));
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

    @Override
    public boolean exists(String isbn) {
        return books.stream().anyMatch(book -> book.getIsbn().equals(isbn));
    }

    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public void update(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(book.getIsbn())) {
                books.set(i, book);
                break;
            }
        }
    }
}
