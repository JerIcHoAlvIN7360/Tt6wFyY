// 代码生成时间: 2025-08-31 05:58:36
package com.example.urlvalidator;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.http.client.HttpClient;
import io.smallrye.mutiny.http.client.HttpClientBuilder;
import io.smallrye.mutiny.http.client.UniformHttpClientException;
import io.vertx.mutiny.core.buffer.Buffer;
import java.net.URL;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/url")
public class UrlValidatorService {

    // HttpClient instance for making HTTP requests
    private static final HttpClient httpClient = HttpClientBuilder.create().build();

    /**
     * Validates the given URL and checks if it's reachable.
     *
     * @param url The URL to be validated.
     * @return A response indicating whether the URL is valid and reachable.
     */
    @GET
    public Uni<Response> validateUrl(@QueryParam("url") String url) {
        try {
            // Check for null or empty URL
            if (url == null || url.trim().isEmpty()) {
                return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST)
                        .entity("URL cannot be null or empty.").build());
            }

            // Validate the URL syntax
            URL urlObject = new URL(url);
            if (!isValidHttpUrl(urlObject)) {
                return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST)
                        .entity("The provided URL is not valid.").build());
            }

            // Check if the URL is reachable
            return httpClient.get(url)
                    .send()
                    .onItem().transformToUniAndSwitch(this::checkHttpResponse)
                    .onFailure(UniformHttpClientException.class)
                    .transformToItem().withFallback(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                            .entity("The URL is not reachable.").build());
        } catch (Exception e) {
            // Handle any unexpected exceptions
            return Uni.createFrom().item(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while validating the URL.").build());
        }
    }

    /**
     * Checks if the URL is a valid HTTP/HTTPS URL.
     *
     * @param url The URL to check.
     * @return True if the URL is valid, false otherwise.
     */
    private boolean isValidHttpUrl(URL url) {
        String protocol = url.getProtocol();
        return "http".equalsIgnoreCase(protocol) || "https".equalsIgnoreCase(protocol);
    }

    /**
     * Checks the HTTP response status and returns the appropriate response.
     *
     * @param response The HTTP response.
     * @return A response indicating the status of the URL.
     */
    private Uni<Response> checkHttpResponse(io.smallrye.mutiny.http.client.HttpResponse<Buffer> response) {
        int statusCode = response.status().code();
        if (statusCode >= 200 && statusCode < 300) {
            // URL is reachable
            return Uni.createFrom().item(Response.status(Response.Status.OK)
                    .entity("The URL is valid and reachable.").build());
        } else {
            // URL is not reachable or has other issues
            return Uni.createFrom().item(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("The URL is not reachable or has other issues.").build());
        }
    }
}
