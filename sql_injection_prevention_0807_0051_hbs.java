// 代码生成时间: 2025-08-07 00:51:26
package com.example.demo;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Main program to prevent SQL injection using Quarkus framework.
 */
@QuarkusMain
public class SqlInjectionPrevention {

    // Injecting the entity manager for database operations
    @PersistenceContext
    EntityManager entityManager;

    public static void main(String... args) {
        Quarkus.run(SqlInjectionPrevention.class);
    }

    /**
     * Function to retrieve a list of users, demonstrating prevention of SQL injection.
     */
    @Transactional
    public List<User> getUsers() {
        try {
            // Using JPQL instead of raw SQL to prevent SQL injection
            String jpql = "SELECT u FROM User u";
            return entityManager.createQuery(jpql, User.class).getResultList();
        } catch (Exception e) {
            // Error handling
            System.err.println("Error fetching users: " + e.getMessage());
        return null;
        }
    }

    /**
     * Function to retrieve a user by ID, demonstrating prevention of SQL injection.
     * @param id The ID of the user to retrieve.
     */
    @Transactional
    public User getUserById(Long id) {
        try {
            // Using named queries to prevent SQL injection
            return entityManager.find(User.class, id);
        } catch (Exception e) {
            // Error handling
            System.err.println("Error fetching user by ID: " + e.getMessage());
        return null;
        }
    }
}

/**
 * JPA entity representing a User.
 */
public class User {
    private Long id;
    private String name;
    private String email;

    // Getters and setters...
}
