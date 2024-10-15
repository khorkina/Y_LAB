package Homework1;

import Homework1.models.User;
import Homework1.utils.Utils;

import java.util.Scanner;

import static Homework1.utils.Utils.waitForEnter;

public class UserAccount {
    private static User currentUser;
    public static void openUserAccount(Scanner scanner, User currentUser) {
        UserAccount.currentUser = currentUser;
        Utils.clearConsole();
        System.out.println("Добро пожаловать, " + currentUser.getName() + "!");
        System.out.println("Это ваш личный кабинет.");

        String input;
        do {
            System.out.println("\nВыберите действие:");
            System.out.println("--------------------");
            System.out.println("1. Профиль пользовтеля");
            System.out.println("2. Мои привычки");
            System.out.println("--------------------");
            System.out.println();
            System.out.println("3. Выход");
            System.out.println();
            System.out.print("Введите номер действия: ");
            System.out.println();
            input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    Utils.clearConsole();
                    showUserMenu(scanner);
                    waitForEnter(scanner);
                    break;
                case "2":
                    Utils.clearConsole();
                    showHabitsMenu(scanner);
                    waitForEnter(scanner);
                    break;
                case "3":
                    System.out.println("Выход из личного кабинета.");
                    logout(scanner);
                    waitForEnter(scanner);
                    return;
                default:
                    System.out.println("Неправильный выбор. Пожалуйста, попробуйте снова.");
                    break;
            }
        } while (!input.equals("3"));

    }

    private static void showHabitsMenu(Scanner scanner) {
        System.out.println("Меню привычек");
        HabitsMenu.getHabitsMenu(scanner, currentUser);

    }

    private static void showUserMenu(Scanner scanner) {
        System.out.println("Профиль пользователя");
        UserMenu.getUserMenu(scanner, currentUser);
    }
    private static void logout(Scanner scanner) {
        ApplicationStarter.setCurrentUser(null);
        System.out.println("Вы успешно вышли из аккаунта.");
        ApplicationStarter.printWelcomeMessage();
        ApplicationStarter.handleUserInput();
    }
}
