// 代码生成时间: 2025-08-14 06:40:01
package com.quarkus.accesscontrol;
# 改进用户体验

import io.quarkus.security.ForbiddenException;
import io.quarkus.security.UnauthorizedException;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
# FIXME: 处理边界情况
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
# NOTE: 重要实现细节
import java.security.Principal;
# TODO: 优化性能

@Path("/access")
public class AccessControlService {

    @Inject
    SecurityContext securityContext;

    /**
# 扩展功能模块
     * A method that requires a user to be authenticated and have the 'admin' role to access.
     * @return A success message if the user is authorized.
     */
    @GET
    @Path("/admin")
    @RolesAllowed("admin")
# FIXME: 处理边界情况
    public Response adminAccess() {
        String username = getUsername();
        return Response.ok("Hello, admin user: " + username).build();
    }

    /**
     * A method that requires a user to be authenticated and have the 'user' role to access.
# 增强安全性
     * @return A success message if the user is authorized.
     */
    @GET
    @Path("/user")
    @RolesAllowed("user")
    public Response userAccess() {
        String username = getUsername();
        return Response.ok("Hello, user: " + username).build();
# NOTE: 重要实现细节
    }

    /**
     * A method that is publicly accessible without any role restrictions.
# 添加错误处理
     * @return A success message indicating it's a public method.
     */
    @GET
# FIXME: 处理边界情况
    @Path("/public")
    public Response publicAccess() {
        return Response.ok("This is a public method.").build();
    }

    private String getUsername() {
        Principal principal = securityContext.getUserPrincipal();
        if (principal != null) {
            return principal.getName();
        } else {
            throw new UnauthorizedException("User is not authenticated.");
        }
    }
}
