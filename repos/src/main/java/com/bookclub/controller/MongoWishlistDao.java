package com.bookclub.controller;

import com.bookclub.model.WishlistItem;
import com.bookclub.dao.impl.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    // Corrected list() method to get all wishlist items
    @Override
    public List<WishlistItem> list() {
        return mongoTemplate.findAll(WishlistItem.class); // This retrieves all items from MongoDB
    }

    // Corrected add() method to save a new wishlist item
    @Override
    public void add(WishlistItem wishlistItem) {
        mongoTemplate.save(wishlistItem); // This saves the item to MongoDB
    }

    // Implement other CRUD methods like remove and update
    @Override
    public boolean remove(WishlistItem wishlistItem) {
        mongoTemplate.remove(wishlistItem); // This removes the item from MongoDB
        return true;
    }

    @Override
    public WishlistItem find(String id) {
        return mongoTemplate.findById(id, WishlistItem.class); // Find by id
    }

    @Override
    public void update(WishlistItem wishlistItem) {
        mongoTemplate.save(wishlistItem); // This will update the existing item in MongoDB if the id exists
    }
}
