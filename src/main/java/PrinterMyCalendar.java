import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class PrinterMyCalendar {

    protected MyCalendar myCalendar;
    protected Point pointStopOutput = new Point(0,0);
    protected int[][] monthCalendar;

    protected WeekLayout weekLayout;

    protected String startOutput(){
        DateFormat dateFormat = new SimpleDateFormat("MMMM Y");
        return beginParagraph() + dateFormat.format(myCalendar.getTime()) + endParagraph();
    }

    protected String headerOutput(){
        String s = "";
        s += beginRow();
        s += weekLayout.header(this);
        s += endRow();
        return s;
    }

    protected String mainOutput(){
        String s = outputMonthPrev();
        s += outputMonthThis();
        s += outputMonthNext();
        return s;
    }

    protected String outputMonthPrev(){
        String s = "";
        s += beginRow();
        for (int weekday=0; weekday<myCalendar.getFirstDayOfMonthIndex(); weekday++)
            s += beginItemAnotherDay() + formattedThisDay(0,weekday) + endItem();
        return s;
    }

    protected String outputMonthThis(){
        int indexFirstDayOfMonth = myCalendar.getFirstDayOfMonthIndex();

        String s = "";
        for (int week=0; week< MyCalendar.WEEKS_COUNT; week++){
            if (indexFirstDayOfMonth == 0)
                s += beginRow();
            for (int weekday=indexFirstDayOfMonth; weekday< MyCalendar.WEEK_SIZE; weekday++){

                s += choiceColorForThisDay(week,weekday) + formattedThisDay(week,weekday) + endItem();

                if (isEndMonth(week, weekday)){
                    pointStopOutput.setLocation(week,weekday+1);
                    return s;
                }
            }
            s += endRow();
            indexFirstDayOfMonth = 0;
            pointStopOutput.setLocation(week,0);
        }
        return s;
    }

    private boolean isEndMonth(int week, int weekday) {
        return (monthCalendar[week][weekday] == myCalendar.getAmountOfDaysInMonth());
    }

    private String choiceColorForThisDay(int week, int weekday) {
        if (isToday(week,weekday))
            return beginItemToday();
        else if (isHoliday(weekday))
            return beginItemHoliday();
        else
            return beginItemWeekday();
    }

    protected boolean isToday(int week, int weekday){
        return (monthCalendar[week][weekday] == myCalendar.getToday());// && numberMonthRelativeThis == 0)
    }

    protected boolean isHoliday(int weekday){
        return (weekday == weekLayout.firstHolidayIndex || weekday == weekLayout.secondHolidayIndex);
    }

    protected String outputMonthNext(){
        String s = "";
        int startIndexWEEKS_COUNT = pointStopOutput.x;
        int startIndexWEEK_SIZE = pointStopOutput.y;
        for (int week=startIndexWEEKS_COUNT; week< MyCalendar.WEEKS_COUNT; week++){
            for (int weekday=startIndexWEEK_SIZE; weekday< MyCalendar.WEEK_SIZE; weekday++){
                s += beginItemAnotherDay() + formattedThisDay(week,weekday) + endItem();
            }
            s += endRow();
            startIndexWEEK_SIZE = 0;
        }
        return s;
    }

    protected String formattedThisDay(int i, int j){
        return String.format("%4d", monthCalendar[i][j]);
    }

    protected String endOutput(){
        return "";
    }

    public void setMyCalendar(MyCalendar myCalendar) {
        this.myCalendar = myCalendar;
    }

    public void setMonthCalendar(int[][] monthCalendar) {
        this.monthCalendar = monthCalendar;
    }

    public void setWeekLayout(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
    }

    public void output(MyCalendar myCalendar){
        setMyCalendar(myCalendar);
        setMonthCalendar(myCalendar.getMonthCalendar());
        setWeekLayout(myCalendar.getWeekLayout());
        String s = startOutput();
        s += headerOutput();
        s += mainOutput();
        s += endOutput();
        formattedOutput(s);
    }

    protected abstract String beginParagraph();
    protected abstract String endParagraph();
    protected abstract String beginRow();
    protected abstract String endRow();
    protected abstract String beginItemToday();
    protected abstract String beginItemWeekday();
    protected abstract String beginItemHoliday();
    protected abstract String beginItemAnotherDay();
    protected abstract String endItem();

    protected abstract void formattedOutput(String s);
}

/*
    startOutput
    headerOutput
    mainOutput
        outputMonthPrev
        outputMonthThis
        outputMonthNext
    endOutput
*/