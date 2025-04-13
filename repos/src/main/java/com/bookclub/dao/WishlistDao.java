// File: src/main/java/dao/impl/MongoWishlistDao.java

package com.bookclub.dao;

import model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.bookclub.service.GenericDao;

import java.util.List;

@Repository("wishlistDao")
public class WishlistDao implements GenericDao<WishlistItem, String> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<WishlistItem> list() {
        return extracted();
    }

    private List<WishlistItem> extracted() {
        return mongoTemplate.findAll(WishlistItem.class);
    }

    @Override
    public void add(WishlistItem entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public void update(WishlistItem entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public boolean remove(WishlistItem entity) {
        mongoTemplate.remove(entity);
        return true;
    }

    @Override
    public WishlistItem find(String key) {
        // Find a WishlistItem by its 'key' (e.g., ISBN or ID)
        return mongoTemplate.findById(key, WishlistItem.class);
    }

    @Override
    public WishlistItem findById(String key) {
        return mongoTemplate.findById(key, WishlistItem.class);
    }
}
