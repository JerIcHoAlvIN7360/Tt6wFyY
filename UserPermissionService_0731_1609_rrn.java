// 代码生成时间: 2025-07-31 16:09:20
package com.example.service;

import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserPermissionService {

    // A simple in-memory database to store user permissions
    private final Map<String, Set<String>> userPermissions;

    // Constructor
    public UserPermissionService() {
        this.userPermissions = new HashMap<>();
    }

    /**
     * Initialize the user permissions system at startup.
     * @param event The startup event.
     */
    public void onStart(@Observes StartupEvent event) {
        // Initialize default permissions here
    }

    /**
     * Grants a permission to a user.
     * @param userId The ID of the user.
     * @param permission The permission to grant.
     */
    public void grantPermission(String userId, String permission) {
        userPermissions.computeIfAbsent(userId, k -> ConcurrentHashMap.newKeySet()).add(permission);
    }

    /**
     * Revokes a permission from a user.
     * @param userId The ID of the user.
     * @param permission The permission to revoke.
     */
    public void revokePermission(String userId, String permission) {
        if (userPermissions.containsKey(userId)) {
            userPermissions.get(userId).remove(permission);
        } else {
            throw new IllegalArgumentException("User not found: " + userId);
        }
    }

    /**
     * Checks if a user has a specific permission.
     * @param userId The ID of the user.
     * @param permission The permission to check.
     * @return True if the user has the permission, false otherwise.
     */
    public boolean hasPermission(String userId, String permission) {
        return userPermissions.containsKey(userId) && userPermissions.get(userId).contains(permission);
    }

    /**
     * Lists all permissions for a user.
     * @param userId The ID of the user.
     * @return A set of permissions for the user.
     */
    public Set<String> listPermissions(String userId) {
        return userPermissions.getOrDefault(userId, ConcurrentHashMap.newKeySet());
    }

    /**
     * Removes a user and all associated permissions.
     * @param userId The ID of the user to remove.
     */
    public void removeUser(String userId) {
        userPermissions.remove(userId);
    }
}
