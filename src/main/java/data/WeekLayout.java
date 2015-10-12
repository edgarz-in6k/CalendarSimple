package data;

import print.PrinterMyCalendar;

public enum WeekLayout {
    STANDARD(
            0,
            DayWeek.SATURDAY.value,
            DayWeek.SUNDAY.value
    ) {
        @Override
        public String[] header() {
            String[] s = {" Mon", " Thu", " Wed", " Tue",  "Fri", " Sat", " Sun"};

            return s;
            /*return weekday(DayWeek.MONDAY.name, printerMyCalendar) +
                    weekday(DayWeek.THURSDAY.name, printerMyCalendar) +
                    weekday(DayWeek.WEDNESDAY.name, printerMyCalendar) +
                    weekday(DayWeek.TUESDAY.name, printerMyCalendar) +
                    weekday(DayWeek.FRIDAY.name, printerMyCalendar) +
                    holiday(DayWeek.SATURDAY.name, printerMyCalendar) +
                    holiday(DayWeek.SUNDAY.name, printerMyCalendar);*/
        }
    },
    AMERICAN(
            1,
            DayWeek.SUNDAY.valueAmerican,
            DayWeek.SUNDAY.valueAmerican
    ) {
        @Override
        public String[] header() {
            String[] s = {" Mon", " Thu", " Wed", " Tue",  "Fri", " Sat", " Sun"};

            return s;
            /*return holiday(DayWeek.SUNDAY.name, printerMyCalendar) +
                    weekday(DayWeek.MONDAY.name, printerMyCalendar) +
                    weekday(DayWeek.THURSDAY.name, printerMyCalendar) +
                    weekday(DayWeek.WEDNESDAY.name, printerMyCalendar) +
                    weekday(DayWeek.TUESDAY.name, printerMyCalendar) +
                    weekday(DayWeek.FRIDAY.name, printerMyCalendar) +
                    weekday(DayWeek.SATURDAY.name, printerMyCalendar);*/
        }
    };
/*
MONDAY(0, " Mon"),
    TUESDAY(1, " Thu"),
    WEDNESDAY(2, " Wed"),
    THURSDAY(3, " Tue"),
    FRIDAY(4, " Fri"),
    SATURDAY(5, " Sat"),
    SUNDAY(6, " Sun");

*/
    int offset;
    int firstHolidayIndex;
    int secondHolidayIndex;

    WeekLayout(int offset, int firstHolidayIndex, int secondHolidayIndex) {
        this.offset = offset;
        this.firstHolidayIndex = firstHolidayIndex;
        this.secondHolidayIndex = secondHolidayIndex;
    }

    /*String weekday(String name, PrinterMyCalendar printerMyCalendar){
        return printerMyCalendar.openWeekMonthDayToken() + name + printerMyCalendar.closeDayToken();
    }

    String holiday(String name, PrinterMyCalendar printerMyCalendar){
        return printerMyCalendar.openHolidayMonthDayToken() + name + printerMyCalendar.closeDayToken();
    }*/

    public abstract String[] header();
}
