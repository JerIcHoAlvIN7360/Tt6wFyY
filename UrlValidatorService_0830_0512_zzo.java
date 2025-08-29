// 代码生成时间: 2025-08-30 05:12:05
package com.example.urlvalidator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/url-validator")
public class UrlValidatorService {

    @GET
    public Response validateUrl(@QueryParam("url") String urlToValidate) {
        // Check if the URL is null or empty
        if (urlToValidate == null || urlToValidate.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("URL cannot be null or empty.").build();
        }

        try {
            // Attempt to create a URI object with the provided URL string
            URI uri = new URI(urlToValidate);
            // Check if the URI is valid
            if (uri.getScheme() == null || uri.getHost() == null) {
                // URL is not valid
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL.").build();
            } else {
                // URL is valid
                return Response.ok("URL is valid.").build();
            }
        } catch (URISyntaxException e) {
            // Handle the case where the URL is not in the correct format
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL format.").build();
        }
    }
}