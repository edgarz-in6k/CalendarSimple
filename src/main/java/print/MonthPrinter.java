package print;

import data.DayWeek;
import data.WeekLayout;
import month.Day;
import month.Month;
import month.Week;

import java.util.Calendar;

public abstract class MonthPrinter {

    protected Month month;
    protected WeekLayout weekLayout;

    protected String startWrite(){
        return openParagraphToken() +
                month.getMonth() + ", " +
                month.getYear() +
                closeParagraphToken();
    }

    protected String headerWrite(){
        String s = "";
        s += openWeekToken();
        s += header();
        s += closeWeekToken();
        return s;
    }

    private String header(){
        String s = "";
        DayWeek[] dayWeeks = weekLayout.header();
        for (DayWeek day : dayWeeks){
            if (weekLayout.isHolidayHeader(day))
                s += openHolidayMonthDayToken();
            else
                s += openWeekMonthDayToken();
            s += formattedDay(day.name) + closeDayToken();
        }
        return s;
    }

    protected String mainWrite(){
        String s = "";
        for (Week week : month){
            s += openWeekToken();
            for (Day day : week)
                s += choiceOpenDayToken(day) + formattedDay(day) + closeDayToken();
            s += closeWeekToken();
        }
        return s;
    }

    private String choiceOpenDayToken(Day day){
        if (!day.isInMonth(month))
            return openOtherMonthDayToken();
        else if (day.isDayEquals(Calendar.getInstance()))
            return openMonthTodayToken();
        else if (day.isHoliday())
            return openHolidayMonthDayToken();
        else
            return openWeekMonthDayToken();
    }

    private String formattedDay(Day day){
        return String.format("%4d", day.getDayOfMonth());
    }

    private String formattedDay(String day){
        return String.format("%4s", day);
    }

    protected String endWrite(){
        return "";
    }

    private void setWeekLayout(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public void output(Month month){
        setMonth(month);
        setWeekLayout(month.getWeekLayout());
        String s = startWrite();
        s += headerWrite();
        s += mainWrite();
        s += endWrite();
        outputToStream(s);
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

    protected abstract void outputToStream(String s);


}

/*
    startWrite
    headerWrite
    mainWrite
    endWrite
*/