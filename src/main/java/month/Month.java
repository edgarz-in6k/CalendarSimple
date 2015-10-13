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
    private WeekLayout weekLayout;

    public Month(Calendar calendar, WeekLayout weekLayout) {
        setWeekLayout(weekLayout);
        Calendar tempCalendar = createTemporaryCalendar(calendar);
        init(calendar, tempCalendar);
        createWeeks(tempCalendar);
    }

    private Calendar createTemporaryCalendar(Calendar calendar){
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(calendar.getTime());

        return tempCalendar;
    }

    private void init(Calendar calendar, Calendar tempCalendar){
        month = tempCalendar.get(Calendar.MONTH);
        year = tempCalendar.get(Calendar.YEAR);
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1);
        tempCalendar.add(Calendar.DAY_OF_WEEK, - calendar.get(Calendar.DAY_OF_WEEK));
        tempCalendar.add(Calendar.DAY_OF_WEEK,  - weekLayout.OFFSET_RELATIVE_OF_STANDARD);
    }

    private void createWeeks(Calendar tempCalendar){
        for (int i = 0; i < WEEK_COUNT; i++) {
            week.add(new Week(tempCalendar, weekLayout));
            tempCalendar.add(Calendar.WEEK_OF_MONTH, 1);
        }
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

    public void setWeekLayout(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
    }

    public WeekLayout getWeekLayout() {
        return weekLayout;
    }
}