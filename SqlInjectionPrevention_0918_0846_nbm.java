// 代码生成时间: 2025-09-18 08:46:45
package com.example;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/sql-injection")
public class SqlInjectionPrevention {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Retrieves a list of users based on a query parameter to demonstrate
     * the prevention of SQL injection.
     *
     * @param username The username to filter by.
     * @return A list of users or an error message if an exception occurs.
     */
    @GET
    @Path("/users")
    @Transactional
    public Response getUsers(@QueryParam("username") String username) {
        try {
            // Using JPA Criteria API or Typed Query to prevent SQL injection
            String jpql = "SELECT u FROM User u WHERE u.username = :username";
            List<User> users = entityManager.createQuery(jpql, User.class)
                .setParameter("username", username)
                .getResultList();
            return Response.ok(users).build();
        } catch (Exception e) {
            // Handle any exceptions that may occur during the query
            return Response.serverError().entity("An error occurred: " + e.getMessage()).build();
        }
    }

    /**
     * Main method for running the Quarkus application.
     */
    @QuarkusMain
    public static class Main implements QuarkusApplication {
        @Inject
        SqlInjectionPrevention sqlInjectionPrevention;

        @Override
        public int run(String... args) throws Exception {
            sqlInjectionPrevention.getUsers("exampleUser");
            return 0;
        }
    }
}

/**
 * JPA entity representing a user.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Constructors, hashCode, equals, and toString methods
}