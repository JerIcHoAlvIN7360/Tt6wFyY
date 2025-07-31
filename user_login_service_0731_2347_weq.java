// 代码生成时间: 2025-07-31 23:47:48
package com.example.auth;

import io.quarkus.runtime.Quarkus;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.quarkus.security.Authenticated;
import io.quarkus.security.Credentials;
import io.quarkus.security.ForbiddenException;
import io.quarkus.security.identity.AuthenticationRequest;
import io.quarkus.security.identity.AuthenticationResponse;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import java.util.Optional;

@Path("/auth")
@ApplicationScoped
public class UserLoginService {

    // User credentials for demonstration purposes
    // In a real application, these would be stored in a secure database
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    private static final String INVALID_CREDENTIALS = "Invalid username or password";
    private static final String SUCCESS = "User authenticated successfully";
    private static final String FAILED = "Authentication failed";

    // Simulate a database lookup for simplicity
    private boolean isUserValid(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(String username, String password) {

        if (!isUserValid(username, password)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(INVALID_CREDENTIALS).build();
        }

        return Response.ok(SUCCESS).build();
    }

    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Authenticated
    public Response verify(SecurityIdentity identity) {

        if (identity == null || !identity.hasRole("user")) {
            throw new ForbiddenException("User not authenticated or lacks required role");
        }

        return Response.ok(SUCCESS).build();
    }

    public static void main(String[] args) {
        Quarkus.run(Quarkus.class, args);
    }
}
