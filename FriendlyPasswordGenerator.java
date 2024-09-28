import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FriendlyPasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        System.out.println("Welcome to the Password Generator!");
        int length = 0;
        
        while (true) {
            System.out.print("How long would you like your password to be? ");
            try {
                length = scanner.nextInt();
                if (length > 0) break; // Valid input
                System.out.println("Please enter a positive integer.");
            } catch (InputMismatchException e) {
                System.out.println("That's not a valid number. Please enter a positive integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        boolean includeUppercase = askYesNo(scanner, "Would you like to include uppercase letters?");
        boolean includeLowercase = askYesNo(scanner, "Would you like to include lowercase letters?");
        boolean includeNumbers = askYesNo(scanner, "Would you like to include numbers?");
        boolean includeSpecialChars = askYesNo(scanner, "Would you like to include special characters?");

        String password = generatePassword(length, includeUppercase, includeLowercase, includeNumbers, includeSpecialChars, random);
        System.out.println("Here is your new password: " + password);
        scanner.close();
    }

    private static boolean askYesNo(Scanner scanner, String question) {
        System.out.print(question + " (yes/no): ");
        return scanner.next().equalsIgnoreCase("yes");
    }

    private static String generatePassword(int length, boolean uCase, boolean lCase, boolean num, boolean special, SecureRandom random) {
        StringBuilder pool = new StringBuilder();
        StringBuilder password = new StringBuilder();

        if (uCase) pool.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        if (lCase) pool.append("abcdefghijklmnopqrstuvwxyz");
        if (num) pool.append("0123456789");
        if (special) pool.append("!@#$%^&*()_-=+<>");

        if (pool.length() == 0) {
            return "Oops! You need to select at least one character type.";
        }

        while (password.length() < length) {
            int index = random.nextInt(pool.length());
            password.append(pool.charAt(index));
        }

        return password.toString();
    }
}
