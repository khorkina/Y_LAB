package Homework1.controller;

import Homework1.dao.HabitManager;
import Homework1.models.Habit;
import Homework1.models.User;
import Homework1.ui.ApplicationStarter;
import Homework1.utils.Frequency;

import java.util.Scanner;

public class HabitsController {
    private static HabitManager habitManager = new HabitManager();
    private static User currentUser = ApplicationStarter.getCurrentUser();

    public static void createHabit(Scanner scanner) {
        System.out.print("Введите название привычки: ");
        String habitName = scanner.nextLine().trim();
        System.out.print("Введите описание привычки: ");
        String habitDescription = scanner.nextLine().trim();
        System.out.print("Введите частоту выполнения (ежедневно, еженедельно): ");
        String frequencyInput = scanner.nextLine().trim();

        try {
            Frequency frequency = Frequency.fromString(frequencyInput);
            Habit newHabit = habitManager.createHabit(currentUser, habitName, habitDescription, frequency);
            System.out.println("Привычка создана: " + newHabit.getTitle() + " с частотой " + newHabit.getFrequency().getDescription());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void viewHabits() {
        System.out.println("Ваши привычки:");
        habitManager.viewHabits(currentUser);
    }

    public static void trackHabit(Scanner scanner) {
        System.out.print("Введите название привычки для отметки выполнения: ");
        String habitName = scanner.nextLine().trim();
        habitManager.trackHabit(currentUser, habitName);
    }

    public static void showHabitStatistics(Scanner scanner) {
        System.out.print("Введите название привычки для отображения статистики: ");
        String habitName = scanner.nextLine().trim();
        habitManager.showHabitStatistics(currentUser, habitName);
    }

    public static void calculateCurrentStreak(Scanner scanner) {
        System.out.print("Введите название привычки для подсчета текущей серии: ");
        String habitName = scanner.nextLine().trim();
        habitManager.calculateCurrentStreak(currentUser, habitName);
    }


    public static void calculateSuccessRate(Scanner scanner) {
        System.out.print("Введите название привычки для подсчета процента успеха: ");
        String habitName = scanner.nextLine().trim();
        System.out.print("Введите количество дней для анализа: ");
        int days = scanner.nextInt();
        scanner.nextLine();
        habitManager.calculateSuccessRate(currentUser, habitName, days);
    }
    public static void deleteHabit(Scanner scanner) {
        System.out.print("Введите название привычки: ");
        String habitName = scanner.nextLine().trim();
        for (Habit habit: currentUser.getHabits()){
            if (habit.getTitle().equals(habitName)){
                habitManager.deleteHabit(currentUser, habit);
                return;
            }
        }
        System.out.println("Привычка не найдена");
    }

    public void setUser(User mockUser) {
    }
}
