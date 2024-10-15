package Homework1;

import Homework1.dao.UserManager;
import Homework1.models.User;
import Homework1.utils.Utils;

import java.util.Scanner;

import static Homework1.utils.Utils.validateEmail;
import static Homework1.utils.Utils.validatePassword;


public class ApplicationStarter {
    private static UserManager userManager = new UserManager();
    private static User currentUser = null;

    public static void run() {
        printWelcomeMessage();
        handleUserInput();
    }

    public static void setCurrentUser(User currentUser) {
        ApplicationStarter.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void printWelcomeMessage() {
        System.out.println("**************************************************");
        System.out.println("*          Добро пожаловать в приложение          *");
        System.out.println("*          для отслеживания ваших привычек        *");
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Войдите в систему, указав свой логин и пароль.");
        System.out.println("Или введите 'регистрация', чтобы создать новый аккаунт.");
        System.out.println("Для выхода введите 'выход'.");
        System.out.println();
        System.out.println("Формат логина: email (например, example@mail.com)");
        System.out.println("Формат пароля: минимум 8 символов, содержащий буквы и цифры.");
        System.out.println();
    }

    public static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (currentUser == null) {
            System.out.print("Введите команду (1: вход, 2: регистрация, 3: выход): ");
            input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "2":
                    Utils.clearConsole();
                    System.out.println("Запуск процесса регистрации...");
                    registerUser(scanner);
                    break;
                case "1":
                    Utils.clearConsole();
                    System.out.println("Запуск процесса авторизации...");
                    loginUser(scanner);
                    break;
                case "3":
                    Utils.clearConsole();
                    System.out.println("Выход из системы. До свидания!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неизвестная команда. Пожалуйста, попробуйте еще раз.");
                    break;
            }
        }
        if (currentUser != null) {
            UserAccount.openUserAccount(scanner, currentUser);
        }

    }


    private static void registerUser(Scanner scanner) {
        System.out.print("Введите логин (email): ");
        String email = scanner.nextLine().trim();
        System.out.print("Введите пароль (мин. 8 символов, буквы и цифры): ");
        String password = scanner.nextLine().trim();
        System.out.print("Введите имя: ");
        String name = scanner.nextLine().trim();

        if (validateEmail(email) && validatePassword(password)) {
            userManager.registerUser(name, email, password);
            System.out.println("Теперь вы можете войти в систему используя логин (email) и пароль!");
        } else {
            System.out.println("Ошибка: Неправильный формат логина или пароля.");
        }
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Введите логин (email): ");
        String email = scanner.nextLine().trim();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine().trim();

        if (userManager.loginUser(email, password) != null) {
            currentUser = userManager.getUserByEmail(email);
            System.out.println("Авторизация прошла успешно!");
        } else {
            System.out.println("Ошибка: Неправильный логин или пароль.");
        }
    }




}