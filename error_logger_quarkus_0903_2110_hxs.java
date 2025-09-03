// 代码生成时间: 2025-09-03 21:10:42
package com.example.errorlogging;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path("/error-logger")
@ApplicationScoped
public class ErrorLoggerQuarkus {
    // Injecting the logging mechanism
    @Inject
    org.jboss.logging.Logger logger;

    // Using AtomicReference to keep the logs thread-safe
    private final AtomicReference<List<String>> logs = new AtomicReference<>(new ArrayList<>());

    // Method to handle incoming error logs
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response logError(String errorData) {
        try {
            // Validate the error data
            if (errorData == null || errorData.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error data cannot be null or empty.").build();
            }

            // Log the error to the internal logs
            String logEntry = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " - " + errorData;
            logs.getAndUpdate(list -> {
                List<String> newList = new ArrayList<>(list);
                newList.add(logEntry);
                return newList;
            });

            // Log the error to the console using the Quarkus logger
            logger.error(errorData);

            return Response.accepted().entity("Error logged successfully.").build();
        } catch (Exception e) {
            // Handle any unexpected exceptions
            logger.error("Failed to log error.", e);
            return Response.serverError().entity("Failed to log error.").build();
        }
    }

    // Method to retrieve the collected error logs
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getLogs() {
        return new ArrayList<>(logs.get());
    }

    // Method to clear the collected error logs
    @GET
    @Path("/clear")
    @Produces(MediaType.TEXT_PLAIN)
    public Response clearLogs() {
        logs.set(new ArrayList<>());
        return Response.ok().entity("Logs cleared successfully.").build();
    }

    // Observer method to handle application startup
    public void onStart(@Observes StartupEvent ev) {
        logger.info("Error Logger application is starting...");
    }

    // Observer method to handle application shutdown
    public void onShutdown(@Observes ShutdownEvent ev) {
        logger.info("Error Logger application is shutting down...");
    }
}
