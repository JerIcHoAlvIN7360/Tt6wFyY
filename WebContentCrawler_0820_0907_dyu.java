// 代码生成时间: 2025-08-20 09:07:21
package org.acme.crawler;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Path("/craw/{url}")
public class WebContentCrawler {

    private static final HttpClient httpClient = HttpClient
            .newBuilder()
            .version(Version.HTTP_2)
            .build();

    @GET
    @QuarkusMain
    public CompletableFuture<String> crawlContent(
            @QueryParam("url") String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                        .header("Accept", "text/html")
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    return response.body();
                } else {
                    throw new RuntimeException("Failed to crawl content: HTTP status code " + response.statusCode());
                }
            } catch (IOException | InterruptedException | ExecutionException e) {
                throw new RuntimeException("Error crawling web content", e);
            }
        });
    }
}
