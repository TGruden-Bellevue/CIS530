package impl;

import dao.WishlistDao;
import model.WishlistItem;

import java.util.ArrayList;
import java.util.List;

public class MemWishlistDao implements WishlistDao {

    private List<WishlistItem> wishlist = new ArrayList<>();

    public MemWishlistDao() {
        wishlist.add(new WishlistItem("123456", "Harry Potter"));
        wishlist.add(new WishlistItem("654321", "Percy Jackson"));
    }

    @Override
    public List<WishlistItem> list() {
        return wishlist;
    }

    @Override
    public WishlistItem find(String isbn) {
        return wishlist.stream()
                .filter(item -> item.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }
}
