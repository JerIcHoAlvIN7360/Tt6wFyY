// 代码生成时间: 2025-09-02 11:51:05
package com.yourcompany.project;

import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents a simple data model for a User entity.
 */
@Entity
@Table(name = "users")
@RegisterForReflection
public class User {

    @Id
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 1, max = 100)
    private String email;

    // Default constructor for JPA
    public User() {}

    // Constructor with parameters for creating a new user
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Additional methods can be added here for business logic
    // ...
}
