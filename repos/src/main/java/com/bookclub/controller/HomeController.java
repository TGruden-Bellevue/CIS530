package com.bookclub.controller;

import com.bookclub.model.Book;
import com.bookclub.dao.impl.BookDao;
import com.bookclub.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home") // Maps to /home URL path
public class HomeController {

    @Autowired
    private WishlistService wishlistService; // Service to manage wishlist

    @Autowired
    private BookDao bookDao; // Inject BookDao instead of BookService

    @GetMapping() // When the user visits "/home", this method is called
    public String showHomePage(Model model) {
        // Fetch the list of books directly from the BookDao
        List<Book> books = bookDao.list(); // Get the list of books from BookDao
        model.addAttribute("books", books); // Add the books to the model

        // Optionally add wishlist items
        model.addAttribute("wishlistItems", wishlistService.list()); // Get wishlist items from WishlistService

        // Return the name of the HTML template to render
        return "home"; // Renders home.html (Thymeleaf template)
    }
}
