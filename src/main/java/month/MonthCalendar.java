package month;

import data.WeekLayout;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MonthCalendar implements Iterable<WeekCalendar> {

    private static final int WEEK_COUNT = 6;
    private final List<WeekCalendar> week = new ArrayList<>();
    private Month month;
    private int year;
    private WeekLayout weekLayout;

    public MonthCalendar(LocalDate localDate, WeekLayout weekLayout) {
        setWeekLayout(weekLayout);
        init(localDate);
        LocalDate tempLocalDate = createTemporaryLocalDate(localDate);
        tempLocalDate = setStartCalendar(weekLayout, tempLocalDate);
        createWeeks(tempLocalDate);
    }

    private void init(LocalDate localDate) {
        month = localDate.getMonth();
        year = localDate.getYear();
    }

    private LocalDate createTemporaryLocalDate(LocalDate localDate) {
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
    }

    private LocalDate setStartCalendar(WeekLayout weekLayout, LocalDate tempLocalDate) {
        while (tempLocalDate.getDayOfWeek() != weekLayout.getStartDayOfWeek()){
            tempLocalDate = tempLocalDate.minusDays(1);
        }
        return tempLocalDate;
    }

    private void createWeeks(LocalDate tempLocalDate) {
        for (int i = 0; i < WEEK_COUNT; i++) {
            week.add(new WeekCalendar(tempLocalDate, this.weekLayout));
            tempLocalDate = tempLocalDate.plusWeeks(1);
        }
    }

    public Month getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public Iterator<WeekCalendar> iterator() {
        return week.iterator();
    }

    public void setWeekLayout(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
    }

    public WeekLayout getWeekLayout() {
        return weekLayout;
    }
}