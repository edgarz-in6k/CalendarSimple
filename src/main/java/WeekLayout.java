public enum WeekLayout {
    STANDARD(
            0,
            Day.SATURDAY.value(),
            Day.SUNDAY.value()
    ) {
        @Override
        public String header(ColorSchema colorSchema) {
            return " " + colorSchema.weekday("Mon  Thu  Wed  Tue  Fri  ") +
                    colorSchema.holiday("Sat  Sun");
        }
    },
    AMERICAN(
            1,
            Day.MONDAY.value(),
            Day.MONDAY.value()
    ) {
        @Override
        public String header(ColorSchema colorSchema) {
            return " " + colorSchema.holiday("Sun  ") +
                    colorSchema.weekday("Mon  Thu  Wed  Tue  Fri  Sat");
        }
    };

    private final int offset;
    private final int firstHolidayIndex;
    private final int secondHolidayIndex;

    WeekLayout(int offset, int firstHolidayIndex, int secondHolidayIndex) {
        this.offset = offset;
        this.firstHolidayIndex = firstHolidayIndex;
        this.secondHolidayIndex = secondHolidayIndex;
    }

    public int offset() {
        return offset;
    }

    public int firstHolidayIndex() {
        return firstHolidayIndex;
    }

    public int secondHolidayIndex() {
        return secondHolidayIndex;
    }

    public abstract String header(ColorSchema colorSchema);

}