package Ylab.HabbitApp;

import java.time.LocalDate;

public class HabitTrackingService {

    public void markHabitCompleted(Habit habit) {
        habit.addCompletionDate(LocalDate.now());
        System.out.println("Привычка выполнена за сегодня.");
    }

    public void showHabitStatistics(Habit habit) {
        System.out.println("Привычка: " + habit.getTitle());
        System.out.println("Всего выполнений: " + habit.getCompletionDates().size());
        System.out.println("Последнее выполнение: " + (habit.getCompletionDates().isEmpty() ? "Не выполнено" : habit.getCompletionDates().get(habit.getCompletionDates().size() - 1)));
    }
}
