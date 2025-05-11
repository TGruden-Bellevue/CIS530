package com.bookclub.controller;

import com.bookclub.model.WishlistItem;
import com.jayway.jsonpath.Criteria;
import com.bookclub.dao.impl.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.management.Query;

@Repository
public class MongoWishlistDao implements WishlistDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    // Get wishlist items for a specific user
    @SuppressWarnings("unchecked")
    @Override
    public List<WishlistItem> list(String username) {
        return (List<WishlistItem>) mongoTemplate.findById(
                new Query(),
                WishlistItem.class);
    }

    // Update a wishlist item
    @Override
    public void update(WishlistItem wishlistItem) {
        mongoTemplate.save(wishlistItem); // Save updates in MongoDB
    }

    // Remove a wishlist item
    @Override
    public boolean remove(String id) {
        WishlistItem item = mongoTemplate.findById(id, WishlistItem.class);
        if (item != null) {
            mongoTemplate.remove(item);
            return true;
        }
        return false;
    }

    // Find a wishlist item by ID
    @Override
    public WishlistItem find(String id) {
        return mongoTemplate.findById(id, WishlistItem.class);
    }

    @Override
    public void add(WishlistItem wishlistItem) {
        mongoTemplate.save(wishlistItem); // Add new item to MongoDB
    }

    @Override
    public List<WishlistItem> list() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public boolean remove(WishlistItem wishlistItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
}