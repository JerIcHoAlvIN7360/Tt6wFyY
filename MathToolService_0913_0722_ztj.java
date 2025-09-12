// 代码生成时间: 2025-09-13 07:22:21
package com.example.mathtools;

import io.quarkus.logging.Log;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/math")
public class MathToolService {

    // Provides the sum of two numbers
    @GET
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public double add(double num1, double num2) {
        try {
            return num1 + num2;
        } catch (Exception e) {
            Log.error("Error calculating sum", e);
            throw new RuntimeException("Error calculating sum");
        }
    }

    // Provides the difference of two numbers
    @GET
    @Path("/subtract")
    @Produces(MediaType.APPLICATION_JSON)
    public double subtract(double num1, double num2) {
        try {
            return num1 - num2;
        } catch (Exception e) {
            Log.error("Error calculating difference", e);
            throw new RuntimeException("Error calculating difference");
        }
    }

    // Provides the product of two numbers
    @GET
    @Path("/multiply")
    @Produces(MediaType.APPLICATION_JSON)
    public double multiply(double num1, double num2) {
        try {
            return num1 * num2;
        } catch (Exception e) {
            Log.error("Error calculating product", e);
            throw new RuntimeException("Error calculating product");
        }
    }

    // Provides the division of two numbers
    @GET
    @Path("/divide")
    @Produces(MediaType.APPLICATION_JSON)
    public double divide(double num1, double num2) {
        try {
            if (num2 == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return num1 / num2;
        } catch (ArithmeticException e) {
            Log.error("Error calculating division: " + e.getMessage(), e);
            throw new RuntimeException("Error calculating division: Cannot divide by zero");
        } catch (Exception e) {
            Log.error("Error calculating division", e);
            throw new RuntimeException("Error calculating division");
        }
    }
}
