package month;

import data.WeekLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeekCalendar implements Iterable<DayCalendar> {

    private static final int WEEK_SIZE = 7;
    private List<DayCalendar> days = new ArrayList<>();

    public WeekCalendar(LocalDate localDate, WeekLayout weekLayout) {
        LocalDate tempLocalDate = createTemporaryLocalDate(localDate);
        createDays(tempLocalDate, weekLayout);
    }

    private LocalDate createTemporaryLocalDate(LocalDate localDate) {
        return LocalDate.from(localDate);
    }

    private void createDays(LocalDate tempLocalDate, WeekLayout weekLayout) {
        for (int i = 0; i < WEEK_SIZE; i++) {
            days.add(new DayCalendar(tempLocalDate, weekLayout));
            tempLocalDate = tempLocalDate.plusDays(1);
        }
    }

    @Override
    public Iterator<DayCalendar> iterator() {
        return days.iterator();
    }
}