// 代码生成时间: 2025-09-17 11:09:28
package com.example.themeservice;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A service to manage theme preferences. It allows switching between different themes.
 */
@ApplicationScoped
@Singleton
public class ThemeService {

    // A map to store user preferences for themes
    private final Map<String, String> themePreferences = new ConcurrentHashMap<>();

    // Injected logger for logging purposes
    @Inject
    @Named("logger")
    private Logger logger;

    /**
     * Switches the theme for a given user.
     *
     * @param userId The unique identifier for the user.
     * @param themeName The name of the theme to switch to.
     * @return The name of the theme that was switched to.
     */
    public String switchTheme(String userId, String themeName) {
        try {
            if (userId == null || themeName == null) {
                logger.error("User ID or theme name cannot be null.");
                throw new IllegalArgumentException("User ID or theme name cannot be null.");
            }

            // Check if the theme is supported
            if (!isSupportedTheme(themeName)) {
                logger.error("Theme '%s' is not supported.", themeName);
                throw new IllegalArgumentException("Theme '%s' is not supported.", themeName);
            }

            // Store the theme preference for the user
            themePreferences.put(userId, themeName);
            logger.info("Theme switched to '%s' for user '%s'.", themeName, userId);
            return themeName;
        } catch (Exception e) {
            logger.error("Error switching theme for user '%s': %s", userId, e.getMessage());
            throw new RuntimeException("Error switching theme.", e);
        }
    }

    /**
     * Checks if the provided theme is supported.
     *
     * @param themeName The name of the theme to check.
     * @return True if the theme is supported, false otherwise.
     */
    private boolean isSupportedTheme(String themeName) {
        // This method can be extended to check against a list of supported themes
        // For simplicity, we assume all themes are supported
        return true;
    }

    /**
     * Retrieves the currently selected theme for a user.
     *
     * @param userId The unique identifier for the user.
     * @return The name of the theme, if found, otherwise an empty optional.
     */
    public Optional<String> getThemeForUser(String userId) {
        return Optional.ofNullable(themePreferences.get(userId));
    }
}
