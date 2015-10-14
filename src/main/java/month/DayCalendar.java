package month;

import data.WeekLayout;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class DayCalendar {

    private int dayOfMonth;
    private DayOfWeek dayOfWeek;
    private Month monthNumber;
    private int year;
    private WeekLayout weekLayout;

    public DayCalendar(LocalDate localDate, WeekLayout weekLayout) {
        init(localDate, weekLayout);
    }

    private void init(LocalDate localDate, WeekLayout weekLayout){
        this.weekLayout = weekLayout;
        dayOfMonth = localDate.getDayOfMonth();
        dayOfWeek = localDate.getDayOfWeek();
        monthNumber = localDate.getMonth();
        year = localDate.getYear();
    }

    public boolean isWeekend() {
        return weekLayout.isWeekend(dayOfWeek);
    }

    public boolean isInMonth(MonthCalendar month) {
        return (monthNumber == month.getMonth() && year == month.getYear());
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Month getMonthNumber() {
        return monthNumber;
    }

    public int getYear() {
        return year;
    }
}