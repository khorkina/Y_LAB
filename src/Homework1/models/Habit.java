package Homework1.models;

import Homework1.utils.Frequency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Habit {
    private String title;
    private String description;
    private Frequency frequency;
    private boolean completed;
    private List<LocalDate> completionDates;

    public Habit(String title, String description, Frequency frequency) {
        this.title = title;
        this.description = description;
        this.frequency = frequency;
        this.completionDates = new ArrayList<>();
        this.completed = false;
    }


    public void markAsCompleted() {
        this.completed = true;
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habit habit = (Habit) o;
        return completed == habit.completed && Objects.equals(title, habit.title) && Objects.equals(description, habit.description) && frequency == habit.frequency && Objects.equals(completionDates, habit.completionDates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, frequency, completed, completionDates);
    }
}
