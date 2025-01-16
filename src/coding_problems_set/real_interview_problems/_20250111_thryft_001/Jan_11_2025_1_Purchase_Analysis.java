package coding_problems_set.real_interview_problems._20250111_thryft_001;

import java.util.*;

public class Jan_11_2025_1_Purchase_Analysis {
    public static void main(String[] args) {
        // Sample data
        List<String> purchases = Arrays.asList(
                "1234 1 book",
                "1234 2 book",
                "1234 2 electronic",
                "1234 3 clothing",
                "1234 4 food",
                "1234 4 shoes",

                "5678 1 book",
                "5678 1 book",
                "5678 1 headphones",// same day, counts as 2 items
                "5678 2 electronic",
                "5678 2 phoneCase",
                "5678 2 headphones",
                "5678 3 bag"
        );

        List<String> result = findQualifiedUsers(purchases);
        System.out.println("Qualified Users: " + result);
    }
    public List<String> findQualifiedUsersNoRepeats(List<String> purchases) {
        // Map to store user's purchases by day
        Map<String, Map<Integer, Set<String>>> userDayCategories = new HashMap<>();
        // Map to store unique categories per user
        Map<String, Set<String>> userCategories = new HashMap<>();

        // Process each purchase record
        for (String purchase : purchases) {
            String[] parts = purchase.split(" ");
            String userId = parts[0];
            int day = Integer.parseInt(parts[1]);
            String category = parts[2];
            // Initialize maps if needed
            userDayCategories.putIfAbsent(userId, new HashMap<>());
            userDayCategories.get(userId).putIfAbsent(day, new HashSet<>());
            userCategories.putIfAbsent(userId, new HashSet<>());
            // Add category to both maps
            userDayCategories.get(userId).get(day).add(category);
            userCategories.get(userId).add(category);
        }
        Set<String> qualifiedUsers = new HashSet<>();
        // Check each user against both conditions
        for (String userId : userCategories.keySet()) {
            if (meetsConditions(userId, userDayCategories, userCategories))
                qualifiedUsers.add(userId);
        }
        // Convert to list and sort
        List<String> result = new ArrayList<>(qualifiedUsers);
        Collections.sort(result);
        return result;
    }

    private boolean meetsConditions(String userId,
            Map<String, Map<Integer, Set<String>>> userDayCategories,
            Map<String, Set<String>> userCategories
    ) {
        // Check condition 1: more than 4 categories
        if (userCategories.get(userId).size() <= 4) return false;

        // Check condition 2: more than three stuffs on consecutive days
        Map<Integer, Set<String>> userPurchases = userDayCategories.get(userId);
        int maxDay = userPurchases.keySet().stream()
                .max(Integer::compareTo)
                .orElse(0);

        for (int day = 1; day < maxDay; day++) {
            Set<String> dayPurchases = userPurchases.getOrDefault(day, new HashSet<>());
            Set<String> nextDayPurchases = userPurchases.getOrDefault(day + 1, new HashSet<>());

            if (dayPurchases.size() > 3 && nextDayPurchases.size() > 3) return true;
        }

        return false;
    }

    public static List<String> findQualifiedUsers(List<String> input) {
        Map<String, Set<String>> userCategory = new HashMap<>();
        Map<String, Map<Integer, Integer>> userDayPurchases = new HashMap<>();

        for (String record : input) {
            String[] fields = record.split("\\s+");
            if (fields.length != 3) continue;
            String userId = fields[0];
            String category = fields[2];
            int day = Integer.parseInt(fields[1]);

            userCategory.putIfAbsent(userId, new HashSet<>());
            userCategory.get(userId).add(category);
            userDayPurchases.putIfAbsent(userId, new HashMap<>());
            Map<Integer, Integer> dayCount = userDayPurchases.get(userId);
            dayCount.put(day, dayCount.getOrDefault(day, 0) + 1);
        }
        List<String> qualifiedUsers = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : userCategory.entrySet()) {
            String userId = entry.getKey();
            Set<String> categories = entry.getValue();
            Map<Integer, Integer> dayCount = userDayPurchases.get(userId);
            if (categories.size() < 4) continue;
            if (hasTwoConsecutiveDaysWithMoreThanThreePurchases(dayCount))
                qualifiedUsers.add(userId);
        }
        Collections.sort(qualifiedUsers);
        return qualifiedUsers;
    }

    private static boolean hasTwoConsecutiveDaysWithMoreThanThreePurchases(Map<Integer, Integer> dayCount) {
        List<Integer> days = new ArrayList<>(dayCount.keySet());
        Collections.sort(days);

        for (int i = 0; i < days.size() - 1; i++) {
            int day1 = days.get(i);
            int day2 = days.get(i + 1);
            int count1 = dayCount.getOrDefault(day1, 0);
            int count2 = dayCount.getOrDefault(day2, 0);

            if (day2 == day1 + 1 && count1 >= 3 && count2 >= 3) {
                return true;
            }
        }

        return false;
    }
}
