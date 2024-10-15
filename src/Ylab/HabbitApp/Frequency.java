package Ylab;

public enum Frequency {
    DAILY("ежедневно"),
    WEEKLY("еженедельно");

    private String description;

    Frequency(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Frequency fromString(String input) throws IllegalArgumentException {
        for (Frequency frequency : Frequency.values()) {
            if (frequency.getDescription().equalsIgnoreCase(input)) {
                return frequency;
            }
        }//todo: need handle
        throw new IllegalArgumentException("Неправильный формат частоты. Допустимые значения: ежедневно, еженедельно.");
    }
}
