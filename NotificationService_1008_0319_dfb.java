// 代码生成时间: 2025-10-08 03:19:21
package com.example.notification;

import io.quarkus.runtime.Quarkus;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.logging.Logger;

/**
 * The interface for the NotificationService.
 * It defines the REST endpoint for notification.
 */
@RegisterRestClient(baseUri = "http://localhost:8080")
interface NotificationClient {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String sendNotification(String notification);
}

/**
 * The NotificationService class which implements notification logic.
 */
@ApplicationScoped
public class NotificationService {
    private static final Logger logger = Logger.getLogger(NotificationService.class);
    private final NotificationClient notificationClient;

    // Constructor injection for NotificationClient
    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    /**
     * Sends a notification to the system.
     *
     * @param notification The notification message to be sent.
     * @return A success message if the notification is sent successfully.
     * @throws InternalServerErrorException if an error occurs while sending the notification.
     */
    public String sendNotification(String notification) {
        try {
            // Call the REST endpoint to send the notification
            String response = notificationClient.sendNotification(notification);

            // Check if the response is successful
            if ("success".equals(response)) {
                return "Notification sent successfully.";
            } else {
                logger.error("Failed to send notification: " + response);
                throw new InternalServerErrorException("Failed to send notification.");
            }
        } catch (Exception e) {
            // Log the exception and throw an InternalServerErrorException
            logger.error("Error sending notification", e);
            throw new InternalServerErrorException("Error sending notification", e);
        }
    }
}
