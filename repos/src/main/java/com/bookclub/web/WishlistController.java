package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.dao.impl.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WishlistController {

    @Autowired
    private WishlistDao wishlistDao; // Use the MongoWishlistDao

    // Show the wishlist page with the list of items
    @RequestMapping("/wishlist")
    public String showWishlist(Model model) {
        model.addAttribute("wishlist", wishlistDao.list()); // Fetch the list from MongoDB
        return "wishlist/list"; // Render the wishlist page
    }

    // Show the form to add a new wishlist item
    @RequestMapping("/wishlist/new")
    public String wishlistForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem()); // Provide a new WishlistItem object
        return "wishlist/new"; // Render the form page
    }

    // Add a new item to the wishlist
    @RequestMapping("/wishlist")
    public String addWishlistItem(@Validated WishlistItem wishlistItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "wishlist/new"; // If there are validation errors, return to the form
        }
        wishlistDao.add(wishlistItem); // Save the new item to MongoDB
        return "redirect:/wishlist"; // Redirect to the wishlist page
    }
}
