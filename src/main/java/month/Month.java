package month;

import java.util.ArrayList;
import java.util.Calendar;

public class Month {

    private final int WEEK_COUNT = 6;
    private final ArrayList<Week> week = new ArrayList<>();
    private int monthOfYear;
    private int year;

    public Month(Calendar calendar){
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(calendar.getTime());

        monthOfYear = tempCalendar.get(Calendar.MONTH);
        year = tempCalendar.get(Calendar.YEAR);

        tempCalendar.set(Calendar.WEEK_OF_MONTH, 1);
        //System.out.println(calendar.getTime());
        for (int i=0; i<WEEK_COUNT; i++){
            week.add(new Week(tempCalendar));
            tempCalendar.add(Calendar.WEEK_OF_MONTH, 1);
        }
    }

    public int getMonthOfYear() {
        return monthOfYear;
    }

    public int getYear() {
        return year;
    }
}