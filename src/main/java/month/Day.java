package month;

import java.util.Calendar;

public class Day {

    private int dayOfWeek;
    private int dayOfMonth;

    private int indexMonth;
    private int indexYear;

    private boolean weekday;

    public Day(Calendar calendar) {
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(calendar.getTime());

        dayOfMonth = tempCalendar.get(Calendar.DAY_OF_MONTH);
        dayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK);

        indexMonth = tempCalendar.get(Calendar.MONTH);
        indexYear = calendar.get(Calendar.YEAR);

        weekday = (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY);
    }

    public boolean isWeekday() {
        return weekday;
    }

    public boolean isInMonth(Month month) {
        return (getIndexMonth() == month.getMonth() && getIndexYear() == month.getYear());
    }

    public boolean isDayEquals(Day day) {
        return (getDayOfMonth() == day.getDayOfMonth() &&
                getIndexMonth() == day.getIndexMonth() &&
                getIndexYear() == day.getIndexYear());
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getIndexMonth() {
        return indexMonth;
    }

    public int getIndexYear() {
        return indexYear;
    }
}