package com.shamirssecret;

import java.math.BigInteger;

public class Utils {
    // Decodes a value from the given base to decimal
   public static int decodeValue(String value, int base) {
        try {
            return new BigInteger(value, base).intValueExact(); // Convert to an exact int
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Value is too large to fit into an int: " + value, e);
        }
    }
    // Lagrange Interpolation to find the constant term (c)
    public static double lagrangeInterpolation(int[][] points) {
        double result = 0.0;

        for (int i = 0; i < points.length; i++) {
            double term = points[i][1]; // y-coordinate
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    term *= (0.0 - points[j][0]) / (points[i][0] - points[j][0]);
                }
            }
            result += term;
        }

        return result;
    }
}
