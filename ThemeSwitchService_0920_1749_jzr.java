// 代码生成时间: 2025-09-20 17:49:01
package com.example.themeswitch;

import javax.enterprise.context.ApplicationScoped;
import io.quarkus.runtime.annotations.RegisterForReflection;

@ApplicationScoped
@RegisterForReflection(targets = {
    Theme.class
})
public class ThemeSwitchService {

    // Enum to represent different themes
    public enum Theme {
        LIGHT,
        DARK
    }

    private Theme currentTheme;

    public ThemeSwitchService() {
        this.currentTheme = Theme.LIGHT; // Default theme
    }

    /**
     * Switches the current theme.
     *
     * @param theme The theme to switch to.
     * @throws IllegalArgumentException If the theme is not LIGHT or DARK.
     */
    public void switchTheme(Theme theme) {
        if (theme == null || (theme != Theme.LIGHT && theme != Theme.DARK)) {
            throw new IllegalArgumentException("Invalid theme: " + theme);
        }
        this.currentTheme = theme;
    }

    /**
     * Gets the current theme.
     *
     * @return The current theme.
     */
    public Theme getCurrentTheme() {
        return this.currentTheme;
    }
}
