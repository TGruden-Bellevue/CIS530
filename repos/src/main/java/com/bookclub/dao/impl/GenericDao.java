package com.bookclub.dao.impl;

import java.util.List;

public interface GenericDao<T, ID> {

    List<T> list(); // List all entities

    void add(T entity); // Add a new entity

    void update(T entity); // Update an existing entity

    boolean remove(T entity); // Remove an entity

    T find(ID id); // Find an entity by its ID

    boolean exists(ID id); // Check if an entity exists by its ID
}
