// 代码生成时间: 2025-10-10 18:33:56
package com.example.attendance;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@QuarkusMain
public class AttendanceSystem {

    // Main method for Quarkus application
    public static void main(String[] args) {
    }

    // Service class for attendance operations
    static class AttendanceService {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Method to record attendance
        @Path("/clock-in")
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String clockIn() {
            LocalDateTime now = LocalDateTime.now();
            return "Clock-in at: " + now.format(formatter);
        }

        // Method to record clock-out
        @Path("/clock-out\)
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String clockOut() {
            LocalDateTime now = LocalDateTime.now();
            return "Clock-out at: " + now.format(formatter);
        }
    }

    // Application entry point
    public static class GreetingResource implements QuarkusApplication {
        @Override
        public int run(String... args) throws Exception {
            // Register attendance service
            new AttendanceService();
            return 0;
        }
    }
}
