package com.bookclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class GenericCrud<T, ID> {

    @Autowired
    private MongoTemplate mongoTemplate;

    // Method to save an entity
    public T save(T entity) {
        return mongoTemplate.save(entity);
    }

    // Method to find an entity by ID
    public T findById(ID id, Class<T> entityClass) {
        return mongoTemplate.findById(id, entityClass);
    }

    // Method to find all entities of a specific class
    public List<T> findAll(Class<T> entityClass) {
        return mongoTemplate.findAll(entityClass);
    }

    // Method to delete an entity by ID
    public void deleteById(ID id, Class<T> entityClass) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, entityClass);
    }

    // Method to check if an entity exists by a specific ID
    public boolean existsById(ID id, Class<T> entityClass) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, entityClass);
    }

    // Method to find an entity by a specific field (for example, name, email, etc.)
    public T findByField(String fieldName, String fieldValue, Class<T> entityClass) {
        Query query = new Query(Criteria.where(fieldName).is(fieldValue));
        return mongoTemplate.findOne(query, entityClass);
    }

    // Method to update an entity (could be expanded for specific fields)
    public void update(T entity) {
        mongoTemplate.save(entity);
    }
}
