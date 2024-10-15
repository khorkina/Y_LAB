package Ylab;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    private static UserManager userManager = new UserManager();
    private static HabitManager habitManager = new HabitManager();
    private static User currentUser = null; // Текущий авторизованный пользователь

    public static void main(String[] args) {
        printWelcomeMessage();

        handleUserInput();
    }

    private static void printWelcomeMessage() {
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

    private static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (currentUser == null) {
            System.out.print("Введите команду (1: вход, 2: регистрация, 3: выход): ");
            input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "2":
                    clearConsole();
                    System.out.println("Запуск процесса регистрации...");
                    registerUser(scanner);
                    break;
                case "1":
                    clearConsole();
                    System.out.println("Запуск процесса авторизации...");
                    loginUser(scanner);
                    break;
                case "3":
                    clearConsole();
                    System.out.println("Выход из системы. До свидания!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неизвестная команда. Пожалуйста, попробуйте еще раз.");
                    break;
            }
        }
        openUserDashboard(scanner);
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

    private static void openUserDashboard(Scanner scanner) {
        clearConsole();
        System.out.println("Добро пожаловать, " + currentUser.getName() + "!");
        System.out.println("Это ваш личный кабинет.");

        String input;
        do {
            System.out.println("\nВыберите действие:");
            System.out.println("--------------------");
            System.out.println("0. Редактировать профиль");
            System.out.println("--------------------");
            System.out.println();
            System.out.println("1. Создать привычку");
            System.out.println("2. Посмотреть мои привычки");
            System.out.println("3. Отметить выполнение привычки");
            System.out.println("4. Показать статистику по привычке");
            System.out.println("5. Выход");

            System.out.print("Введите номер действия: ");
            input = scanner.nextLine().trim();

            switch (input) {
                case "0":
                    clearConsole();
                    System.out.println("Профиль пользователя");
                    showUserMenu(scanner);
                    break;
                case "1":
                    createHabit(scanner);
                    break;
                case "2":
                    viewHabits();
                    break;
                case "3":
                    trackHabit(scanner);
                    break;
                case "4":
                    showHabitStatistics(scanner);
                    break;
                case "5":
                    System.out.println("Выход из личного кабинета.");
                    break;
                default:
                    System.out.println("Неправильный выбор. Пожалуйста, попробуйте снова.");
                    break;
            }
        } while (!input.equals("5"));
    }

    private static void showUserMenu(Scanner scanner) {
        System.out.println("User menu ...");

    }

    private static void createHabit(Scanner scanner) {
        System.out.print("Введите название привычки: ");
        String habitName = scanner.nextLine().trim();
        System.out.print("Введите описание привычки: ");
        String habitDescription = scanner.nextLine().trim();
        System.out.print("Введите частоту выполнения (ежедневно, еженедельно): ");
        String frequencyInput = scanner.nextLine().trim();

        try {
            Frequency frequency = Frequency.fromString(frequencyInput);
            Habit newHabit = habitManager.createHabit(currentUser, habitName, habitDescription, frequency);
            System.out.println("Привычка создана: " + newHabit.getTitle() + " с частотой " + newHabit.getFrequency().getClass());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void viewHabits() {
        System.out.println("Ваши привычки:");
        habitManager.viewHabits(currentUser);
    }

    private static void trackHabit(Scanner scanner) {
        System.out.print("Введите название привычки для отметки выполнения: ");
        String habitName = scanner.nextLine().trim();
        habitManager.trackHabit(currentUser, habitName);
    }

    private static void showHabitStatistics(Scanner scanner) {
        System.out.print("Введите название привычки для отображения статистики: ");
        String habitName = scanner.nextLine().trim();
        habitManager.showHabitStatistics(currentUser, habitName);
    }

    private static boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private static boolean validatePassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*[0-9].*");
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}