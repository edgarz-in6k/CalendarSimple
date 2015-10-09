public enum WeekLayout {
    STANDARD(
            0,
            Day.SATURDAY.value(),
            Day.SUNDAY.value()
    ) {
        @Override
        public String header(ColorSchema colorSchema) {
            return colorSchema.weekday(" Mon") +
                    colorSchema.weekday("  Thu") +
                    colorSchema.weekday("  Wed") +
                    colorSchema.weekday("  Tue") +
                    colorSchema.weekday("  Fri") +
                    colorSchema.holiday("  Sat") +
                    colorSchema.holiday("  Sun");
        }
    },
    AMERICAN(
            1,
            Day.MONDAY.value(),
            Day.MONDAY.value()
    ) {
        @Override
        public String header(ColorSchema colorSchema) {
            return colorSchema.holiday("  Sun") +
                    colorSchema.weekday(" Mon") +
                    colorSchema.weekday("  Thu") +
                    colorSchema.weekday("  Wed") +
                    colorSchema.weekday("  Tue") +
                    colorSchema.weekday("  Fri") +
                    colorSchema.weekday("  Sat");
        }
    };

    private final int offset;
    private final int firstHolidayIndex;
    private final int secondHolidayIndex;
    //private final String[] namesDayOfWeek = {" Mon",  " Thu",  " Wed",  " Tue",  " Fri",  " Sat", " Sun"};

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

    public boolean isHoliday(int i, int j){
        return (j == firstHolidayIndex() || j == secondHolidayIndex());
    }

    //TODO get weekday names form calendar
    public abstract String header(ColorSchema colorSchema);

}