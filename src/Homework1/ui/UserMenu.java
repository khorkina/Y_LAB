package Homework1.ui;

import Homework1.controller.UserController;
import Homework1.models.User;

import java.util.Scanner;

import static Homework1.utils.Utils.clearConsole;
import static Homework1.utils.Utils.waitForEnter;

public class UserMenu {

    public static void getUserMenu(Scanner scanner, User currentUser) {
        clearConsole();
        System.out.println("Добро пожаловать, " + currentUser.getName() + "!");
        System.out.println("Это ваш личный кабинет.");

        String input;
        do {
            System.out.println("\nВыберите действие:");
            System.out.println("--------------------");
            System.out.println("0. Назад");
            System.out.println("--------------------");
            System.out.println("1. Редактировать профиль пользователя");
            System.out.println("2. Сбросить пароль");
            System.out.println("3. Удаление аккаунта");



            if (currentUser.isAdmin()) {
                System.out.println("--------------------");
                System.out.println("Функции администратора: ");
                System.out.println("a. Показать всех пользователей");
                System.out.println("b. Заблокировать пользователя");
                System.out.println("c. Удалить пользователя");
                System.out.println("--------------------");
            }
            System.out.println();
            System.out.print("Введите номер действия: ");
            input = scanner.nextLine().trim();

            switch (input) {
                case "0":
                    clearConsole();
                    System.out.println("Назад");
                    break;
                case "1":
                    clearConsole();
                    UserController.updateUser(currentUser, scanner);
                    waitForEnter(scanner);
                    break;
                case "2":
                    clearConsole();
                    UserController.resetPassword();
                    waitForEnter(scanner);
                    break;
                case "3":
                    clearConsole();
                    UserController.deleteUserAccount(currentUser, scanner);
                    waitForEnter(scanner);
                    break;
                case "a":
                    if (currentUser.isAdmin()) {
                        UserController.viewAllUsers();
                        waitForEnter(scanner);
                    } else {
                        System.out.println("Эта функция доступна только администраторам.");
                    }
                    break;
                case "b":
                    if (currentUser.isAdmin()) {
                        UserController.blockUser(scanner);
                        waitForEnter(scanner);
                    } else {
                        System.out.println("Эта функция доступна только администраторам.");
                    }
                    break;
                case "c":
                    if (currentUser.isAdmin()) {
                        UserController.deleteForeignAccount(currentUser, scanner);
                        waitForEnter(scanner);
                    } else {
                        System.out.println("Эта функция доступна только администраторам.");
                    }
                    break;
                default:
                    clearConsole();
                    System.out.println("Неправильный выбор. Пожалуйста, попробуйте снова.");
                    waitForEnter(scanner);
                    break;
            }
        } while (!input.equals("0"));
        UserAccount.openUserAccount(scanner, currentUser);
    }
}
