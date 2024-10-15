package Ylab;

import jdk.jfr.Frequency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Habit {
    private String title;
    private String description;
    private Frequency frequency; // "ежедневно", "еженедельно"
    private boolean completed;
    private List<LocalDate> completionDates;

    public Habit(String title, String description, Frequency frequency) {
        this.title = title;
        this.description = description;
        this.frequency = frequency;
        this.completionDates = new ArrayList<>();
        this.completed = false;
    }

    // Метод для отметки привычки как выполненной
    public void markAsCompleted() {
        this.completed = true;
    }

    // Метод для проверки, была ли привычка выполнена
    public boolean isCompleted() {
        return completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }


    public List<LocalDate> getCompletionDates() {
        return completionDates;
    }

    public void addCompletionDate(LocalDate date) {
        completionDates.add(date);
    }

    @Override
    public String toString() {
        return "Habit{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", frequency='" + frequency + '\'' +
                ", completionDates=" + completionDates +
                '}';
    }
}
