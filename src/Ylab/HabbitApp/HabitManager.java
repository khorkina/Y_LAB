package Ylab;

import jdk.jfr.Frequency;

import java.util.List;

public class HabitManager {
    private final HabitTrackingService habitTrackingService = new HabitTrackingService();

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
        System.out.println("Привычка удалена.");
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
                habitTrackingService.markHabitCompleted(habit);
                System.out.println("Привычка '" + habitName + "' отмечена как выполненная.");
                return;
            }
        }
        System.out.println("Привычка '" + habitName + "' не найдена.");
    }
    public void showHabitStatistics(User user, String habitName){
        List<Habit> userHabits = user.getHabits();
        for (Habit habit : userHabits) {
            if (habit.getTitle().equalsIgnoreCase(habitName)) {
                habitTrackingService.showHabitStatistics(habit);
                System.out.println("Привычка '" + habitName + "' отмечена как выполненная.");
                return;
            }
        }
        System.out.println("Привычка '" + habitName + "' не найдена.");
    }

    public Habit createHabit(User currentUser, String habitName, String habitDescription, Ylab.Frequency frequency) {
        return null;
    }
}
