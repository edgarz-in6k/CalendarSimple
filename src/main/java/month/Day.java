package month;

import data.WeekLayout;

import java.util.Calendar;

public class Day {

    private int dayOfMonth;
    private int dayOfWeek;
    private int monthNumber;
    private int year;
    private WeekLayout weekLayout;

    public Day(Calendar calendar, WeekLayout weekLayout) {
        init(calendar, weekLayout);
    }

    private void init(Calendar calendar, WeekLayout weekLayout){
        this.weekLayout = weekLayout;
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        monthNumber = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
    }

    public boolean isWeekend() {
        return weekLayout.isWeekend(dayOfWeek);
    }

    public boolean isInMonth(Month month) {
        return (monthNumber == month.getMonth() && year == month.getYear());
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getYear() {
        return year;
    }
}