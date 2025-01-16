package coding_problems_set.real_interview_problems._20250114_virtu_financial_001;

import java.util.ArrayList;
import java.util.List;

/**
 * finding the minimum number of steps required to convert a given integer x into a Fibonacci number
 * by either adding or subtracting 1 in each step,
 */
public class Jan_14_2025_2_Fibonacci_Steps {
    // Function to generate Fibonacci numbers up to a maximum value
    private static List<Long> generateFibonacciUpTo(long max) {
        List<Long> fibs = new ArrayList<>();
        fibs.add(0L);
        fibs.add(1L);
        while (true) {
            long nextFib = fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2);
            if (nextFib > max + 1000) { // Adding buffer to handle edge cases
                break;
            }
            fibs.add(nextFib);
        }
        return fibs;
    }

    // Function to find the minimum steps to the nearest Fibonacci number
    public static long minStepsToFibonacci(long x) {
        // Handle negative x by considering absolute value
        long absX = Math.abs(x);

        // Generate Fibonacci numbers up to absX + some buffer
        List<Long> fibs = generateFibonacciUpTo(absX);

        // Initialize minimum steps with a large number
        long minSteps = Long.MAX_VALUE;

        // Iterate through the Fibonacci list to find the closest number
        for (long fib : fibs) {
            long steps = Math.abs(fib - absX);
            if (steps < minSteps) {
                minSteps = steps;
            }
            // Since Fibonacci numbers are increasing, once steps start increasing, we can break
            else {
                break;
            }
        }

        return minSteps;
    }

    // Main method for testing
    public static void main(String[] args) {
        long[] testCases = {10, 15, 21, 0, 1, 34, 55, -5, 1000000};

        for (long x : testCases) {
            long steps = minStepsToFibonacci(x);
            System.out.println("Minimum steps to convert " + x + " to a Fibonacci number: " + steps);
        }
    }
}

