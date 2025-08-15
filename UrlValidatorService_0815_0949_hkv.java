// 代码生成时间: 2025-08-15 09:49:29
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.QuarkusApplicationLifecycleHelper;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Path("/url-validator")
public class UrlValidatorService {

    // Inject the application lifecycle helper for proper shutdown
    @Inject
    QuarkusApplicationLifecycleHelper lifecycle;

    @PostConstruct
    public void init() {
        // Initialize the application lifecycle
        lifecycle.addShutdownTask(new Runnable() {
            @Override
            public void run() {
                // Perform any necessary cleanup before shutdown
            }
        });
    }

    @GET
    @Path("/check-url")
    public Response checkUrl(@QueryParam("url") String urlString) {
        try {
            // Check if the URL string is null or empty
            if (urlString == null || urlString.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("URL is required")
                        .type(MediaType.TEXT_PLAIN).build();
            }

            // Create a new URL object
            URL url = new URL(urlString);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000); // 5 seconds timeout
            connection.setReadTimeout(5000); // 5 seconds timeout

            // Check the HTTP response code
            int responseCode = connection.getResponseCode();

            // Close the connection
            connection.disconnect();

            // Return the result based on the response code
            if (responseCode >= 200 && responseCode < 300) {
                return Response.status(Response.Status.OK)
                        .entity("URL is valid")
                        .type(MediaType.TEXT_PLAIN).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("URL is invalid")
                        .type(MediaType.TEXT_PLAIN).build();
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the process
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error checking URL: " + e.getMessage())
                    .type(MediaType.TEXT_PLAIN).build();
        }
    }
}
