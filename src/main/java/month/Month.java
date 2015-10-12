package month;

import data.WeekLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class Month implements Iterable<Week> {

    private static final int WEEK_COUNT = 6;
    private final List<Week> week = new ArrayList<>();
    private int month;
    private int year;

    public Month(Calendar calendar, WeekLayout weekLayout) {
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(calendar.getTime());



        tempCalendar.set(Calendar.DAY_OF_MONTH, 1);
        tempCalendar.add(Calendar.DAY_OF_WEEK, -calendar.get(Calendar.DAY_OF_WEEK) - 1);
        for (int i = 0; i < WEEK_COUNT; i++) {
            week.add(new Week(tempCalendar));
            tempCalendar.add(Calendar.WEEK_OF_MONTH, 1);
        }
    }

    private void init(Calendar tempCalendar){
        month = tempCalendar.get(Calendar.MONTH);
        year = tempCalendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public Iterator<Week> iterator() {
        return week.iterator();
    }
}