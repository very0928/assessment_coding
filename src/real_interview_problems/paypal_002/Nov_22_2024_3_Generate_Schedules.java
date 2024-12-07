package real_interview_problems.paypal_002;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nov_22_2024_3_Generate_Schedules {
    public static void main(String[] args) {
        Nov_22_2024_3_Generate_Schedules solver = new Nov_22_2024_3_Generate_Schedules();
        // Test Case 1
        List<String> result1 = solver.generateSchedules("426?5?5", 26, 4);
        System.out.println("Test Case 1: " + result1);

        // Test Case 2
        List<String> result2 = solver.generateSchedules("???8???", 56, 8);
        System.out.println("Test Case 2: " + result2);
    }

    private static void generateCombinations(List<Integer> current, int remainHours,
                                             int numUnscheduledDays, int dayHours,
                                             List<List<Integer>> validCombinations) {
        // Base case: If we have fulfilled all unscheduled days
        if (current.size() == numUnscheduledDays) {
            if (remainHours == 0) {
                validCombinations.add(new ArrayList<>(current));
            }
            return;
        }

        // Try all possible hours for the current unscheduled day
        // from 0 to dayHours
        for (int i = 0; i <= Math.min(dayHours, remainHours); i++) {
            current.add(i);
            generateCombinations(current, remainHours - i, numUnscheduledDays, dayHours, validCombinations);
            current.remove(current.size() - 1);
        }
    }

    public List<String> generateSchedules(String pattern, int workHours, int dayHours) {
        int fixedHours = 0;
        List<Integer> questionMarkIndexes = new ArrayList<>();

        // Go through the pattern and sum fixed hours, track '?' positions
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '?') {
                questionMarkIndexes.add(i);
            } else {
                fixedHours += Character.getNumericValue(pattern.charAt(i));
            }
        }
        // Calculate remaining hours to be distributed
        int remainHours = workHours - fixedHours;
        int numUnscheduledDays = questionMarkIndexes.size();
        if (remainHours < 0 || remainHours > numUnscheduledDays * dayHours) {
            return new ArrayList<>();
        }

        // Generate valid combinations of hours for unscheduled days
        List<List<Integer>> validCombinations = new ArrayList<>();
        generateCombinations(new ArrayList<>(), remainHours, numUnscheduledDays, dayHours, validCombinations);

        // Generate final schedules by filling '?' in the pattern
        List<String> result = new ArrayList<>();
        for (List<Integer> combination : validCombinations) {
            StringBuilder schedule = new StringBuilder(pattern);
            for (int i = 0; i < numUnscheduledDays; i++) {
                schedule.setCharAt(questionMarkIndexes.get(i), Character.forDigit(combination.get(i), 10));
            }
            result.add(schedule.toString());
        }
        // Sort result in lexicographic order
        Collections.sort(result);
        return result;
    }

    public List<String> generateSchedules_1(String pattern, int workHours, int dayHours) {
        int[] knownHours = new int[pattern.length()];
        int unknownDaysCount = 0;
        int remainingHours = workHours;

        // Calculate known hours and count unknown days
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '?') {
                unknownDaysCount++;
            } else {
                knownHours[i] = pattern.charAt(i) - '0';
                remainingHours -= knownHours[i];
            }
        }
        // Early validation
        if (remainingHours < 0 || remainingHours > unknownDaysCount * dayHours) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        generatePermutations(pattern, knownHours, remainingHours, dayHours, 0, result);
        Collections.sort(result);
        return result;
    }

    private void generatePermutations(String pattern, int[] knownHours, int remainingHours, int dayHours, int index, List<String> result) {
        if (index == pattern.length()) {
            if (remainingHours == 0) {
                result.add(buildSchedule(pattern, knownHours));
            }
            return;
        }

        if (pattern.charAt(index) != '?') {
            generatePermutations(pattern, knownHours, remainingHours, dayHours, index + 1, result);
            return;
        }

        for (int hour = 0; hour <= Math.min(remainingHours, dayHours); hour++) {
            knownHours[index] = hour;
            generatePermutations(pattern, knownHours, remainingHours - hour, dayHours, index + 1, result);
        }
    }

    private String buildSchedule(String pattern, int[] hours) {
        StringBuilder schedule = new StringBuilder();
        int hourIndex = 0;
        for (char c : pattern.toCharArray()) {
            if (c == '?') {
                schedule.append(hours[hourIndex++]);
            } else schedule.append(c);
        }
        return schedule.toString();
    }


}
