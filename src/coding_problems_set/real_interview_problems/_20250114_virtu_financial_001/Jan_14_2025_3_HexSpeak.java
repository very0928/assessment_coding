package coding_problems_set.real_interview_problems._20250114_virtu_financial_001;

/**
 * Coding problem: Write a Java function. Given a string encoding a decimal integer n
 * HexSpeak Conversion:
 * - Replace 0 with O and 1 with I.
 * - Check if the characters are valid HexSpeak characters (A-F).
 * <p>
 * returns a string represent the HexSpeak H of n if H is a valid HexSpeak word else "ERROR"
 */
public class Jan_14_2025_3_HexSpeak {

    /**
     * Converts a decimal integer (as a string) to its HexSpeak representation.
     * Returns "ERROR" if the HexSpeak representation contains invalid characters.
     *
     * @param decimalStr The decimal integer as a string.
     * @return The HexSpeak representation or "ERROR" if invalid.
     */
    public static String toHexSpeak_1(String decimalStr) {
        if (decimalStr == null || decimalStr.isEmpty()) {
            return "ERROR";
        }

        try {
            // Parse the input string to a long to handle large numbers
            long number = Long.parseLong(decimalStr);
            if (number < 0) {
                // HexSpeak typically doesn't handle negative numbers
                return "ERROR";
            }

            // Convert the number to its hexadecimal representation in uppercase
            String hex = Long.toHexString(number).toUpperCase();
            StringBuilder hexSpeak = new StringBuilder();

            // Iterate through each hex character and map it to HexSpeak
            for (char c : hex.toCharArray()) {
                switch (c) {
                    case '0':
                        hexSpeak.append('O');
                        break;
                    case '1':
                        hexSpeak.append('I');
                        break;
                    case '2':
                        hexSpeak.append('Z');
                        break;
                    case '3':
                        hexSpeak.append('E');
                        break;
                    case '5':
                        hexSpeak.append('S');
                        break;
                    case '6':
                        hexSpeak.append('G');
                        break;
                    case '7':
                        hexSpeak.append('T');
                        break;
                    case '8':
                        hexSpeak.append('B');
                        break;
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                        hexSpeak.append(c);
                        break;
                    default:
                        // Characters '4' and '9' are invalid in this HexSpeak mapping
                        return "ERROR";
                }
            }

            return hexSpeak.toString();
        } catch (NumberFormatException e) {
            // If the input string is not a valid decimal number
            return "ERROR";
        }
    }

    public static String toHexSpeak(String n) {
        // Convert the decimal number to hexadecimal
        long decimalValue = Long.parseLong(n);
        if (decimalValue < 0) {
            // HexSpeak typically doesn't handle negative numbers
            return "ERROR";
        }
        String hex = Long.toHexString(decimalValue).toUpperCase();

        // Replace valid hexadecimal characters to HexSpeak characters
        StringBuilder hexSpeak = new StringBuilder();
        for (char c : hex.toCharArray()) {
            if (c == '0') {
                hexSpeak.append('O'); // Replace 0 with O
            } else if (c == '1') {
                hexSpeak.append('I'); // Replace 1 with I
            } else if ("ABCDEF".indexOf(c) != -1) {
                hexSpeak.append(c); // Keep valid A-F characters
            } else {
                return "ERROR"; // Invalid character for HexSpeak
            }
        }
        return hexSpeak.toString();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(toHexSpeak("257"));    // Outputs: "IOI"
        System.out.println(toHexSpeak("3"));      // Outputs: "ERROR"
        System.out.println(toHexSpeak("48879"));  // Outputs: "BEEF"

        String[] testInputs = {"15", "16", "19", "20", "255", "257", "1000", "1001", "abc", "-10"};

        for (String input : testInputs) {
            String result = toHexSpeak(input);
            System.out.println("Decimal: " + input + " → HexSpeak: " + result);
        }
    }
}

