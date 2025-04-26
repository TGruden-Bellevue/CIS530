package com.bookclub.dao.impl;

import com.bookclub.model.WishlistItem;
import com.bookclub.dao.impl.GenericCrudDao;
import java.util.List;

public interface WishlistDao extends GenericCrudDao<WishlistItem, String> {
    List<WishlistItem> list(); // List all wishlist items

    void add(WishlistItem wishlistItem); // Add a wishlist item

    boolean remove(WishlistItem wishlistItem); // Remove a wishlist item

    WishlistItem find(String id); // Find a wishlist item by its ID

    void update(WishlistItem wishlistItem); // Update a wishlist item
}
