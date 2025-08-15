// 代码生成时间: 2025-08-16 04:45:28
package com.example.quarkusdemo.services;

import io.quarkus.logging.Log;
import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service responsible for handling security audit logs.
 */
@ApplicationScoped
public class AuditLoggingService {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Logs an audit message with the current timestamp.
     *
     * @param message The message to log.
     * @param level   The log level.
     */
    public void logAudit(String message, LogLevel level) {
        String timestampedMessage = generateTimestampedMessage(message);
        logMessage(timestampedMessage, level);
    }

    /**
     * Generate a timestamped message for auditing.
     *
     * @param message The original message.
     * @return A timestamped message.
     */
    private String generateTimestampedMessage(String message) {
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter) + " - " + message;
    }

    /**
     * Logs a message using the Quarkus logging framework.
     *
     * @param message The message to log.
     * @param level   The log level.
     */
    private void logMessage(String message, LogLevel level) {
        switch (level) {
            case INFO:
                Log.info(message);
                break;
            case WARNING:
                Log.warn(message);
                break;
            case ERROR:
                Log.error(message);
                break;
            default:
                Log.info(message);
                break;
        }
    }

    /**
     * Enum representing different log levels.
     */
    public enum LogLevel {
        INFO, WARNING, ERROR
    }
}
