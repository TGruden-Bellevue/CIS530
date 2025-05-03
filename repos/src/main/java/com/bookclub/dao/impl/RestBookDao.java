package com.bookclub.dao.impl;

import com.bookclub.model.Book;
import com.bookclub.dao.impl.BookDao;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestBookDao implements BookDao {

    private static final String OPEN_LIBRARY_URL = "https://openlibrary.org/api/books";

    // Get book details from the OpenLibrary API
    public String getBooksDoc(String isbnString) {
        // Create RestTemplate and HttpHeaders
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, "application/json");

        // Build the URI with query params
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(OPEN_LIBRARY_URL)
                .queryParam("bibkeys", "ISBN:" + isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "data");

        // Make the GET request
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rest.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        // Parse the response body
        String jsonBooklist = response.getBody();
        return jsonBooklist;
    }

    // Update list() method to use the API and get books
    @Override
    public List<Book> list() {
        String isbn = "9780140328721"; // Sample ISBN
        String jsonResponse = getBooksDoc(isbn);
        // Parse the JSON response and convert it into a List of Book objects
        // (Use a JSON parsing library like JsonPath here)
        return parseJsonToBooks(jsonResponse);
    }

    // Update find() method to use the API and get book by ISBN
    @Override
    public Book find(String isbn) {
        String jsonResponse = getBooksDoc(isbn);
        // Parse the JSON response and convert it into a Book object
        return parseJsonToBook(jsonResponse);
    }

    private List<Book> parseJsonToBooks(String jsonResponse) {
        // Logic to parse JSON and return a list of Book objects
        // For now, return an empty list as a placeholder
        return List.of();
    }

    private Book parseJsonToBook(String jsonResponse) {
        // Logic to parse JSON and return a single Book object
        // For now, return a placeholder Book object
        return new Book("9780140328721", "Sample Book", "This is a sample description",
                "https://openlibrary.org/works/OL26732W", 300);
    }

    @Override
    public boolean exists(String isbn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exists'");
    }

    @Override
    public void add(Book book) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void update(Book book) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
