// 代码生成时间: 2025-08-21 11:43:41
package com.example.xssprotection;

import javax.enterprise.context.ApplicationScoped;
import org.apache.commons.text.StringEscapeUtils;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.smallrye.mutiny.Uni;

@Path("/xssprotection")
@ApplicationScoped
public class XssProtectionService {

    /**
     * Sanitizes the provided user input to prevent XSS attacks.
     * @param userInput The raw user input that may contain XSS payloads.
     * @return A sanitized version of the input.
     */
    @POST
    @Path("/sanitize")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> sanitizeInput(String userInput) {
        // Check for null input to avoid NullPointerException
        if (userInput == null) {
            return Uni.createFrom().deferred(() -> Uni.createFrom().failure(new IllegalArgumentException("Input cannot be null")));
        }

        // Sanitize the input to prevent XSS attacks
        String sanitizedInput = StringEscapeUtils.escapeHtml4(userInput);

        // Return the sanitized input
        return Uni.createFrom().item(sanitizedInput);
    }
}
