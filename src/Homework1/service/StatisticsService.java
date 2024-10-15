package Homework1.service;

import Homework1.models.Habit;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StatisticsService {


    public void calculateCurrentStreak(Habit habit) {
        if (habit.getCompletionDates().isEmpty()) {
            System.out.println("Привычка еще не выполнена.");
            return;
        }

        LocalDate lastCompletion = habit.getCompletionDates().get(habit.getCompletionDates().size() - 1);
        LocalDate today = LocalDate.now();
        long streak = 0;


        if (habit.getFrequency().getDescription().equals("ежедневно")) {
            streak = ChronoUnit.DAYS.between(lastCompletion, today);
        } else if (habit.getFrequency().getDescription().equals("еженедельно")) {
            streak = ChronoUnit.WEEKS.between(lastCompletion, today);
        }

        if (streak == 0) {
            System.out.println("Текущая серия выполнения: привычка выполняется в срок.");
        } else {
            System.out.println("Серия прервана: последний раз привычка выполнена " + streak + " дней/недель назад.");
        }
    }


    public void calculateSuccessRate(Habit habit, int days) {
        LocalDate today = LocalDate.now();
        long successfulDays = habit.getCompletionDates().stream()
                .filter(date -> date.isAfter(today.minusDays(days)))
                .count();

        double successRate = (successfulDays * 100.0) / days;
        System.out.printf("Процент успешного выполнения за %d дней: %.2f%%.%n", days, successRate);
    }


    public void generateProgressReport(Habit habit) {
        System.out.println("Отчет по привычке: " + habit.getTitle());
        System.out.println("Описание: " + habit.getDescription());
        System.out.println("Частота выполнения: " + habit.getFrequency().getDescription());
        System.out.println("Общее количество выполнений: " + habit.getCompletionDates().size());
        if (!habit.getCompletionDates().isEmpty()) {
            System.out.println("Последнее выполнение: " + habit.getCompletionDates().get(habit.getCompletionDates().size() - 1));
        } else {
            System.out.println("Привычка еще не выполнена.");
        }
    }

    public void markHabitCompleted(Habit habit) {
        habit.addCompletionDate(LocalDate.now());
        System.out.println("Привычка выполнена за сегодня.");
    }
}
