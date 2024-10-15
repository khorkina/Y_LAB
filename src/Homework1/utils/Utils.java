package Homework1;

import java.util.Scanner;

public class Utils {
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    public static void waitForEnter(Scanner scanner) {
        System.out.println("\nНажмите Enter, чтобы продолжить...");
        scanner.nextLine();
        clearConsole();
    }
    public static boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean validatePassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*[0-9].*");
    }
}
