package month;

import data.WeekLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class Week implements Iterable<Day> {

    private static final int WEEK_SIZE = 7;
    private List<Day> days = new ArrayList<>();

    public Week(Calendar calendar, WeekLayout weekLayout) {
        Calendar tempCalendar = createTemporaryCalendar(calendar);
        createDays(weekLayout, tempCalendar);
    }

    private Calendar createTemporaryCalendar(Calendar calendar){
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(calendar.getTime());

        return tempCalendar;
    }

    private void createDays(WeekLayout weekLayout, Calendar tempCalendar){
        for (int i = 0; i < WEEK_SIZE; i++) {
            days.add(new Day(tempCalendar, weekLayout));
            tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    @Override
    public Iterator<Day> iterator() {
        return days.iterator();
    }
}