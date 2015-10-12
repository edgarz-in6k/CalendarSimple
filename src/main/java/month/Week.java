package month;

import java.util.ArrayList;
import java.util.Calendar;

public class Week {

    private final int WEEK_SIZE = 7;
    private ArrayList<Day> days = new ArrayList<>();

    Week(Calendar calendar){
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(calendar.getTime());

        for (int i = 0; i<WEEK_SIZE; i++){
            days.add(new Day(tempCalendar));
            tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
}
