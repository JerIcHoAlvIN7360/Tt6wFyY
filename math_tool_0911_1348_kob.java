// 代码生成时间: 2025-09-11 13:48:00
package com.example.math;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * A utility class providing common mathematical operations.
 */
@ApplicationScoped
public class MathTool {

    // Define a MathContext for precise calculations
    private final MathContext mc = new MathContext(10);

    public MathTool() {
        // Constructor is private to prevent instantiation
    }

    //////////////////////////
    // Addition
    //////////////////////////

    /**
     * Adds two numbers with precise arithmetic.
     *
     * @param number1 The first number.
     * @param number2 The second number.
     * @return The sum of the two numbers.
     */
    public BigDecimal add(BigDecimal number1, BigDecimal number2) {
        return number1.add(number2, mc);
    }

    //////////////////////////
    // Subtraction
    //////////////////////////

    /**
     * Subtracts one number from another with precise arithmetic.
     *
     * @param number1 The minuend.
     * @param number2 The subtrahend.
     * @return The result of the subtraction.
     */
    public BigDecimal subtract(BigDecimal number1, BigDecimal number2) {
        return number1.subtract(number2, mc);
    }

    //////////////////////////
    // Multiplication
    //////////////////////////

    /**
     * Multiplies two numbers with precise arithmetic.
     *
     * @param number1 The first number.
     * @param number2 The second number.
     * @return The product of the two numbers.
     */
    public BigDecimal multiply(BigDecimal number1, BigDecimal number2) {
        return number1.multiply(number2, mc);
    }

    //////////////////////////
    // Division
    //////////////////////////

    /**
     * Divides two numbers with precise arithmetic.
     *
     * @param number1 The dividend.
     * @param number2 The divisor.
     * @return The quotient of the division.
     * @throws ArithmeticException If the divisor is zero.
     */
    public BigDecimal divide(BigDecimal number1, BigDecimal number2) {
        if (number2.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return number1.divide(number2, mc);
    }

    // Additional mathematical operations can be added here
    // following the same pattern with proper error handling and documentation.
}
