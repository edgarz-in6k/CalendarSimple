import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class PrinterMyCalendar {

    protected MyCalendar myCalendar;
    protected Coordinate coordinateStopOutput = new Coordinate();
    protected WeekLayout weekLayout;

    protected String startWrite(){
        DateFormat dateFormat = new SimpleDateFormat("MMMM Y");
        return openParagraphToken() + dateFormat.format(myCalendar.getTime()) + closeParagraphToken();
    }

    protected String headerWrite(){
        String s = "";
        s += openWeekToken();
        s += weekLayout.header(this);
        s += closeWeekToken();
        return s;
    }

    protected String mainWrite(){
        String s = writeMonthPrev();
        s += writeMonthThis();
        s += writeMonthNext();
        return s;
    }

    protected String writeMonthPrev(){
        String s = "";
        s += openWeekToken();
        for (int weekday = 0; weekday < myCalendar.getFirstDayOfMonthIndex(); weekday++)
            s += openOtherMonthDayToken() + formattedThisDay(0, weekday) + closeDayToken();
        return s;
    }

    protected String writeMonthThis(){
        int indexFirstDayOfMonth = myCalendar.getFirstDayOfMonthIndex();

        String s = "";
        for (int week=0; week< MyCalendar.WEEKS_COUNT; week++){
            if (indexFirstDayOfMonth == 0)
                s += openWeekToken();
            for (int weekday=indexFirstDayOfMonth; weekday< MyCalendar.WEEK_SIZE; weekday++){

                s += choiceColorForThisDay(week,weekday) + formattedThisDay(week,weekday) + closeDayToken();

                if (isEndMonthThis(week, weekday)){
                    coordinateStopOutput.set(week, weekday + 1);
                    return s;
                }
            }
            s += closeWeekToken();
            indexFirstDayOfMonth = 0;
            coordinateStopOutput.set(week, 0);
        }
        return s;
    }

    private boolean isEndMonthThis(int week, int weekday) {
        return (myCalendar.getDayWithMonthCalendar(week,weekday) == myCalendar.getAmountOfDaysInMonth());
    }

    private String choiceColorForThisDay(int week, int weekday) {
        if (isToday(week,weekday))
            return openMonthTodayToken();
        else if (isHoliday(weekday))
            return openHolidayMonthDayToken();
        else
            return openWeekMonthDayToken();
    }

    protected boolean isToday(int week, int weekday){
        return (myCalendar.getDayWithMonthCalendar(week,weekday) == myCalendar.getToday());// && numberMonthRelativeThis == 0)
    }

    protected boolean isHoliday(int weekday){
        return (weekday == weekLayout.firstHolidayIndex || weekday == weekLayout.secondHolidayIndex);
    }

    protected String writeMonthNext(){
        String s = "";
        int startIndexWEEKS_COUNT = coordinateStopOutput.getX();
        int startIndexWEEK_SIZE = coordinateStopOutput.getY();
        for (int week=startIndexWEEKS_COUNT; week< MyCalendar.WEEKS_COUNT; week++){
            for (int weekday=startIndexWEEK_SIZE; weekday< MyCalendar.WEEK_SIZE; weekday++){
                s += openOtherMonthDayToken() + formattedThisDay(week,weekday) + closeDayToken();
            }
            s += closeWeekToken();
            startIndexWEEK_SIZE = 0;
        }
        return s;
    }

    protected String formattedThisDay(int week, int weekday){
        return String.format("%4d", myCalendar.getDayWithMonthCalendar(week,weekday));
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

class Coordinate {
    private int x;
    private int y;

    Coordinate(){
        x = 0;
        y = 0;
    }

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

/*
    startWrite
    headerWrite
    mainWrite
        writeMonthPrev
        writeMonthThis
        writeMonthNext
    endWrite
*/