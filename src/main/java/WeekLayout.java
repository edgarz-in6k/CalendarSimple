public enum WeekLayout {
    STANDARD(
            0,
            Day.SATURDAY.value,
            Day.SUNDAY.value
    ) {
        @Override
        public String header(PrinterMyCalendar printerMyCalendar) {
            return weekday(Day.MONDAY.name, printerMyCalendar) +
                    weekday(Day.THURSDAY.name, printerMyCalendar) +
                    weekday(Day.WEDNESDAY.name, printerMyCalendar) +
                    weekday(Day.TUESDAY.name, printerMyCalendar) +
                    weekday(Day.FRIDAY.name, printerMyCalendar) +
                    holiday(Day.SATURDAY.name, printerMyCalendar) +
                    holiday(Day.SUNDAY.name, printerMyCalendar);
        }
    },
    AMERICAN(
            1,
            Day.SUNDAY.valueAmerican,
            Day.SUNDAY.valueAmerican
    ) {
        @Override
        public String header(PrinterMyCalendar printerMyCalendar) {
            return holiday(Day.SUNDAY.name, printerMyCalendar) +
                    weekday(Day.MONDAY.name, printerMyCalendar) +
                    weekday(Day.THURSDAY.name, printerMyCalendar) +
                    weekday(Day.WEDNESDAY.name, printerMyCalendar) +
                    weekday(Day.TUESDAY.name, printerMyCalendar) +
                    weekday(Day.FRIDAY.name, printerMyCalendar) +
                    weekday(Day.SATURDAY.name, printerMyCalendar);
        }
    };

    int offset;
    int firstHolidayIndex;
    int secondHolidayIndex;

    WeekLayout(int offset, int firstHolidayIndex, int secondHolidayIndex) {
        this.offset = offset;
        this.firstHolidayIndex = firstHolidayIndex;
        this.secondHolidayIndex = secondHolidayIndex;
    }

    String weekday(String name, PrinterMyCalendar printerMyCalendar){
        return printerMyCalendar.beginItemWeekday() + name + printerMyCalendar.endItem();
    }

    String holiday(String name, PrinterMyCalendar printerMyCalendar){
        return printerMyCalendar.beginItemHoliday() + name + printerMyCalendar.endItem();
    }

    public abstract String header(PrinterMyCalendar printerMyCalendar);
}
