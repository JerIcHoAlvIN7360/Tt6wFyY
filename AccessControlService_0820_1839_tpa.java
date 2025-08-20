// 代码生成时间: 2025-08-20 18:39:15
package com.example.accesscontrol;

import io.quarkus.security.identity.SecurityIdentity;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.security.Principal;

@Path("/access-control")
public class AccessControlService {

    @Inject
    SecurityIdentity securityIdentity;

    // This method is accessible to all users, including anonymous ones.
    @GET
    @PermitAll
    @Path("/public")
    public String publicAccess() {
        return "This is a public resource.";
    }

    // This method is only accessible to authenticated users.
    @GET
    @Path("/private")
    public Response privateAccess() {
        if (securityIdentity.isAnonymous()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized access").build();
        }
        return Response.ok("This is a private resource, accessible only to authenticated users.").build();
    }

    // This method is only accessible to users with the 'admin' role.
    @GET
    @RolesAllowed("admin")
    @Path("/admin")
    public String adminAccess() {
        return "This is an admin resource.";
    }

    // This method is only accessible to users with the 'moderator' role.
    @GET
    @RolesAllowed("moderator\)
    @Path("/moderator\)
    public String moderatorAccess() {
        return "This is a moderator resource.";
    }

    // Helper method to get the current user's principal name.
    public String getCurrentUser() {
        Principal principal = securityIdentity.getPrincipal();
        if (principal != null) {
            return principal.getName();
        }
        return "anonymous";
    }
}
