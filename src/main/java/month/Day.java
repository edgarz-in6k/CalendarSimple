package month;

import java.util.Calendar;

public class Day {

    private int dayOfWeek;
    private int dayOfMonth;

    private int numberMonth;
    private int numberYear;

    private boolean weekday;

    Day(Calendar calendar){
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        numberMonth = calendar.get(Calendar.MONTH);
        numberYear = calendar.get(Calendar.YEAR);

        weekday = (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY);

        System.out.print(dayOfMonth + " ");
        if (dayOfWeek == Calendar.SUNDAY)
            System.out.println();
    }

    boolean isWeekday(){
        return weekday;
    }

    boolean isInMonth(Month month){
        return (numberMonth == month.getMonthOfYear() && numberYear == month.getYear());
    }

    boolean isDayEquals(Day day){
        return (
                getDayOfMonth() == day.getDayOfMonth() &&
                getNumberMonth() == day.getNumberMonth() &&
                getNumberYear() == day.getNumberYear());
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getNumberMonth() {
        return numberMonth;
    }

    public int getNumberYear() {
        return numberYear;
    }
}
