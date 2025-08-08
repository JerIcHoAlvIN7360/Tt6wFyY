// 代码生成时间: 2025-08-08 23:27:19
package com.example.apiformatter;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * ApiResponseFormatter is a class that provides a standardized way to format API responses.
 * It includes error handling and ensures that all responses are in a consistent format.
 */
@ApplicationScoped
@RegisterForReflection
public class ApiResponseFormatter {

    // Define a map to store different response codes and their corresponding messages
    private Map<Integer, String> responseMessages = new HashMap<>();

    public ApiResponseFormatter() {
        initializeResponseMessages();
    }

    /**
     * Initializes the response messages map with standard HTTP status codes and messages.
     */
    private void initializeResponseMessages() {
        responseMessages.put(Response.Status.OK.getStatusCode(), "Success");
        responseMessages.put(Response.Status.BAD_REQUEST.getStatusCode(), "Bad Request");
        responseMessages.put(Response.Status.UNAUTHORIZED.getStatusCode(), "Unauthorized");
        responseMessages.put(Response.Status.FORBIDDEN.getStatusCode(), "Forbidden");
        responseMessages.put(Response.Status.NOT_FOUND.getStatusCode(), "Not Found");
        responseMessages.put(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Internal Server Error");
    }

    /**
     * Formats the API response with a standard structure.
     *
     * @param status The HTTP status code of the response.
     * @param message The message associated with the status code.
     * @param data Any additional data to include in the response.
     * @return A formatted response in JSON format.
     */
    public Response formatResponse(int status, String message, Object data) {
        return Response.status(status)
                .entity(createResponseEntity(status, message, data))
                .build();
    }

    /**
     * Creates a response entity with the provided status, message, and data.
     *
     * @param status The HTTP status code.
     * @param message The message associated with the status code.
     * @param data Any additional data to include in the response.
     * @return A Map representing the response entity.
     */
    private Map<String, Object> createResponseEntity(int status, String message, Object data) {
        Map<String, Object> responseEntity = new HashMap<>();
        responseEntity.put("status", status);
        responseEntity.put("message", message);
        if (data != null) {
            responseEntity.put("data", data);
        }
        return responseEntity;
    }

    /**
     * Exception mapper to handle exceptions and format the error response.
     */
    @Provider
    public static class ApiExceptionMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
            int statusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            String message = "An unexpected error occurred";
            if (exception instanceof CustomApiException) {
                CustomApiException apiException = (CustomApiException) exception;
                statusCode = apiException.getStatusCode();
                message = apiException.getMessage();
            }
            return new ApiResponseFormatter().formatResponse(statusCode, message, null);
        }
    }

    /**
     * Custom API exception class for handling specific API errors.
     */
    public static class CustomApiException extends RuntimeException {

        private int statusCode;

        public CustomApiException(int statusCode, String message) {
            super(message);
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }
}
