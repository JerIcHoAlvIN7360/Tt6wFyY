// 代码生成时间: 2025-08-26 07:19:15
package org.acme.themeswitching;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/theme")
public class ThemeSwitchingService {

    @Inject
    private ThemeStore themeStore; // 注入主题存储服务

    @GET
    @Path("/current")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCurrentTheme() {
        // 获取当前主题
        return themeStore.getCurrentTheme();
    }

    @POST
    @Path("/set")
    public Response setTheme(@PathParam("theme") String theme) {
        try {
            // 设置新的主题
            themeStore.setTheme(theme);
            return Response.ok("Theme set to: " + theme).build();
        } catch (IllegalArgumentException e) {
            // 错误处理
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid theme: " + theme).build();
        }
    }
}

// ThemeStore.java
package org.acme.themeswitching;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("themeStore")
public class ThemeStore {
    private String currentTheme;

    public String getCurrentTheme() {
        return currentTheme;
    }

    public void setTheme(String theme) {
        if (!isValidTheme(theme)) {
            throw new IllegalArgumentException("Invalid theme");
        }
        currentTheme = theme;
    }

    // 检查主题是否有效
    private boolean isValidTheme(String theme) {
        return theme != null && !theme.trim().isEmpty();
    }
}
