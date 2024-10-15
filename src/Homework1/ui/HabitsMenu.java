package Homework1.ui;

import  Homework1.controller.HabitsController;
import  Homework1.models.User;

import java.util.Scanner;

import static  Homework1.controller.HabitsController.*;
import static  Homework1.utils.Utils.clearConsole;
import static  Homework1.utils.Utils.waitForEnter;

public class HabitsMenu {
    private HabitsMenu() {
    }

    public static void getHabitsMenu(Scanner scanner, User currentUser) {
        clearConsole();
        System.out.println("Добро пожаловать, " + currentUser.getName() + "!");
        System.out.println("Это ваш личный кабинет.");

        String input;
        do {
            System.out.println("\nВыберите действие:");
            System.out.println("--------------------");
            System.out.println("0. Назад");
            System.out.println("--------------------");
            System.out.println();
            System.out.println("1. Создать привычку");
            System.out.println("2. Посмотреть мои привычки");
            System.out.println("3. Отметить выполнение привычки");
            System.out.println("4. Показать статистику по привычке");
            System.out.println("5. Удалить привычку");
            System.out.println("------");
            System.out.println("6. Показать статистику привычки");
            System.out.println("7. Рассчитать текущую серию выполнения привычек");
            System.out.println("8. Рассчитать процент успешного выполнения привычки");
            System.out.println("-----");
            System.out.println("9. Выход");
            System.out.print("Введите номер действия: ");
            System.out.println();
            input = scanner.nextLine().trim();

            switch (input) {
                case "0":
                    clearConsole();
                    UserAccount.openUserAccount(scanner, currentUser);
                    waitForEnter(scanner);
                    break;
                case "1":
                    clearConsole();
                    createHabit(scanner);
                    waitForEnter(scanner);
                    break;
                case "2":
                    clearConsole();
                    viewHabits();
                    waitForEnter(scanner);
                    break;
                case "3":
                    clearConsole();
                    trackHabit(scanner);
                    waitForEnter(scanner);
                    break;
                case "4":
                    clearConsole();
                    showHabitStatistics(scanner);
                    waitForEnter(scanner);
                    break;
                case "5":
                    clearConsole();
                    deleteHabit(scanner);
                    waitForEnter(scanner);
                    break;
                case "6":
                    clearConsole();
                    HabitsController.showHabitStatistics(scanner);
                    waitForEnter(scanner);
                    break;
                case "7":
                    clearConsole();
                    HabitsController.calculateCurrentStreak(scanner);
                    waitForEnter(scanner);
                    break;
                case "8":
                    clearConsole();
                    HabitsController.calculateSuccessRate(scanner);
                    waitForEnter(scanner);
                    break;
                case "9":
                    clearConsole();
                    System.out.println("Выход из личного кабинета.");
                    clearConsole();
                    break;
                default:
                    System.out.println("Неправильный выбор. Пожалуйста, попробуйте снова.");
                    waitForEnter(scanner);
                    break;
            }
        } while (!input.equals("9"));
    }
}
