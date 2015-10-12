package print;

import data.WeekLayout;
import month.MyCalendar;
import month.Day;
import month.Week;

import java.util.Calendar;

public abstract class PrinterMyCalendar {

    protected MyCalendar myCalendar;
    protected WeekLayout weekLayout;

    protected String startWrite(){
        return openParagraphToken() +
                myCalendar.getMonth().getMonth() + ", " +
                myCalendar.getMonth().getYear() +
                closeParagraphToken();
    }

    protected String headerWrite(){
        String s = "";
        s += openWeekToken();
        //s += weekLayout.header(this);
        s += closeWeekToken();
        return s;
    }

    protected String mainWrite(){
        String s = "";

        for (Week week : myCalendar.getMonth()){
            s += openWeekToken();
            for (Day day : week){
                if (!day.isInMonth(myCalendar.getMonth())){
                    s += openOtherMonthDayToken() + formattedDay(day) + closeDayToken();
                }
                else if (day.isDayEquals(new Day(Calendar.getInstance()))){
                    s += openMonthTodayToken() + formattedDay(day) + closeDayToken();
                }
                else if (!day.isWeekday()){
                    s += openHolidayMonthDayToken() + formattedDay(day) + closeDayToken();
                }
                else {
                    s += openWeekMonthDayToken() + formattedDay(day) + closeDayToken();
                }
            }
            s += closeWeekToken();
        }

        return s;
    }

    private String formattedDay(Day day){
        return String.format("%4d", day.getDayOfMonth());
    }

    protected String endWrite(){
        return "";
    }

    private void setMyCalendar(MyCalendar myCalendar) {
        this.myCalendar = myCalendar;
    }

    private void setWeekLayout(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
    }

    public void output(MyCalendar myCalendar){
        setMyCalendar(myCalendar);
        setWeekLayout(myCalendar.getWeekLayout());
        String s = startWrite();
        s += headerWrite();
        s += mainWrite();
        s += endWrite();
        formattedOutput(s);
    }

    protected abstract String openParagraphToken();
    protected abstract String closeParagraphToken();
    protected abstract String openWeekToken();
    protected abstract String closeWeekToken();
    protected abstract String openMonthTodayToken();
    protected abstract String openWeekMonthDayToken();
    protected abstract String openHolidayMonthDayToken();
    protected abstract String openOtherMonthDayToken();
    protected abstract String closeDayToken();

    protected abstract void formattedOutput(String s);


}

/*
    startWrite
    headerWrite
    mainWrite
    endWrite
*/