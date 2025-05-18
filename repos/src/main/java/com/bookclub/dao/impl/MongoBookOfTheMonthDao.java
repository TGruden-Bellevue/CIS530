package com.bookclub.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bookclub.dao.BooKOfTheMonthDao;

import com.bookclub.model.BookOfTheMonth;

@Repository("bookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BooKOfTheMonthDao {

    private final List<BookOfTheMonth> books = new ArrayList<>();

    @Override
    public void add(BookOfTheMonth item) {
        books.add(item);
    }

    @Override
    public boolean remove(String id) {
        return books.removeIf(book -> book.getId().equals(id));
    }

    @Override
    public List<BookOfTheMonth> list(String key) {
        // For now, return all items; you can filter by key if needed
        return new ArrayList<>(books);
    }

    @Override
    public void update(BookOfTheMonth item) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(item.getId())) {
                books.set(i, item);
                return;
            }
        }
    }

    @Override
    public BookOfTheMonth find(String id) {
        Optional<BookOfTheMonth> result = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
        return result.orElse(null);

    }
}
