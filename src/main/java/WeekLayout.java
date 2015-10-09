public enum WeekLayout {
    STANDARD(
            0,
            Day.SATURDAY.value(),
            Day.SUNDAY.value()
    ),
    AMERICAN(
            1,
            Day.MONDAY.value(),
            Day.MONDAY.value()
    );

    private final int offsetForAmerican;
    private final int firstHolidayIndex;
    private final int secondHolidayIndex;

    WeekLayout(int offsetForAmerican, int firstHolidayIndex, int secondHolidayIndex) {
        this.offsetForAmerican = offsetForAmerican;
        this.firstHolidayIndex = firstHolidayIndex;
        this.secondHolidayIndex = secondHolidayIndex;
    }

    public int offsetForAmerican() {
        return offsetForAmerican;
    }

    public int firstHolidayIndex() {
        return firstHolidayIndex;
    }

    public int secondHolidayIndex() {
        return secondHolidayIndex;
    }

    public String header(ColorSchema colorSchema) {
        if (this == WeekLayout.STANDARD)
            return " " + colorSchema.COLOR_WEEKDAY + "Mon  Thu  Wed  Tue  Fri  " + colorSchema.COLOR_RESET +
                    colorSchema.COLOR_HOLIDAY + "Sat  Sun" + colorSchema.COLOR_RESET;
        else
            return " " + colorSchema.COLOR_HOLIDAY + "Sun  " + colorSchema.COLOR_RESET +
                    colorSchema.COLOR_WEEKDAY + "Mon  Thu  Wed  Tue  Fri  Sat" + colorSchema.COLOR_RESET;
    }
}