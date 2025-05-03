package com.bookclub.controller;

import com.bookclub.model.WishlistItem;
import com.bookclub.dao.impl.WishlistDao;
import com.bookclub.controller.MongoWishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
public class WishlistRestController {

    private WishlistDao wishlistDao;

    @Autowired
    public void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = new MongoWishlistDao(); // Instantiate MongoWishlistDao here
    }

    // Method to return all wishlist items
    @GetMapping
    public List<WishlistItem> showWishlist() {
        return wishlistDao.list(); // Return all wishlist items
    }

    // Method to find a wishlist item by id
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public WishlistItem findById(@PathVariable String id) {
        return wishlistDao.find(id); // Find a wishlist item by ID
    }
}
