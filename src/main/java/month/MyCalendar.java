package month;

import data.WeekLayout;
import month.Month;

import java.util.Calendar;

public class MyCalendar {

    private Month month;
    private WeekLayout weekLayout;

    public MyCalendar(){
        this(WeekLayout.STANDARD);
    }

    public MyCalendar(WeekLayout weekLayout){
        setWeekLayout(weekLayout);
        buildCalendar();
    }

    private void buildCalendar(){
        Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.MONTH, 4);////////////////////
        //calendar.add(Calendar.MONTH, 1);////////////////////
        //calendar.add(Calendar.MONTH, -1);////////////////////
        month = new Month(calendar, weekLayout);
    }

    public WeekLayout getWeekLayout() {
        return weekLayout;
    }

    public void setWeekLayout(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
    }

    public Month getMonth() {
        return month;
    }
}