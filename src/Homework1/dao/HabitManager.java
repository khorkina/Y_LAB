package Homework1.dao;

import Homework1.models.Habit;
import Homework1.models.User;
import Homework1.service.StatisticsService;
import Homework1.utils.Frequency;

import java.util.List;

public class HabitManager {
    private final StatisticsService statisticsService = new StatisticsService();


    public Habit createHabit(User user, String title, String description, Frequency frequency) {
        Habit habit = new Habit(title, description, frequency);
        user.getHabits().add(habit);
        System.out.println("Привычка добавлена.");

        return habit;
    }


    public void editHabit(Habit habit, String newTitle, String newDescription, Frequency newFrequency) {
        habit.setTitle(newTitle);
        habit.setDescription(newDescription);
        habit.setFrequency(newFrequency);
        System.out.println("Привычка изменена.");
    }


    public void deleteHabit(User user, Habit habit) {
        user.getHabits().remove(habit);
        System.out.println("Привычка успешно удалена.");
    }


    public void viewHabits(User user) {
        if (user.getHabits().isEmpty()) {
            System.out.println("У вас нет привычек.");
        } else {
            for (Habit habit : user.getHabits()) {
                System.out.println("Привычка: " + habit.getTitle() + ", Описание: " + habit.getDescription());
            }
        }
    }

    public void trackHabit(User user, String habitName) {
        List<Habit> userHabits = user.getHabits();
        for (Habit habit : userHabits) {
            if (habit.getTitle().equalsIgnoreCase(habitName)) {
                statisticsService.markHabitCompleted(habit);
                System.out.println("Привычка '" + habitName + "' отмечена как выполненная.");
                return;
            }
        }
        System.out.println("Привычка '" + habitName + "' не найдена.");
    }

    public void showHabitStatistics(User user, String habitName) {
        List<Habit> userHabits = user.getHabits();
        for (Habit habit : userHabits) {
            if (habit.getTitle().equalsIgnoreCase(habitName)) {
                statisticsService.generateProgressReport(habit);
                return;
            }
        }
        System.out.println("Привычка '" + habitName + "' не найдена.");
    }

    public void calculateCurrentStreak(User user, String habitName) {
        List<Habit> userHabits = user.getHabits();
        for (Habit habit : userHabits) {
            if (habit.getTitle().equalsIgnoreCase(habitName)) {
                statisticsService.calculateCurrentStreak(habit);
                return;
            }
        }
        System.out.println("Привычка '" + habitName + "' не найдена.");
    }

    public void calculateSuccessRate(User user, String habitName, int days) {
        List<Habit> userHabits = user.getHabits();
        for (Habit habit : userHabits) {
            if (habit.getTitle().equalsIgnoreCase(habitName)) {
                statisticsService.calculateSuccessRate(habit, days);
                return;
            }
        }
        System.out.println("Привычка '" + habitName + "' не найдена.");
    }
}
