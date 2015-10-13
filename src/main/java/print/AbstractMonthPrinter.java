package print;

import data.WeekLayout;
import month.Day;
import month.Month;
import month.Week;

import java.io.PrintStream;
import java.text.DateFormatSymbols;
import java.util.Calendar;

public abstract class AbstractMonthPrinter {

    public static final String COMMA_AND_SPACE = ", ";
    protected Month month;
    protected WeekLayout weekLayout;

    public AbstractMonthPrinter(Month month){
        setMonth(month);
        setWeekLayout(month.getWeekLayout());
    }

    public void printToStream(PrintStream printStream){
        printStream.print(output());
    }

    private String output(){
        String result = "";

        result += buildBegin();
        result += buildTitle();
        result += buildHeader();
        result += buildMonth();
        result += buildEnd();

        return result;
    }

    protected String buildBegin() {
        return "";
    }


    protected String buildTitle(){
        return openTitleToken() +
                month.getMonth() + COMMA_AND_SPACE + month.getYear() +
                closeTitleToken();
    }

    private String buildHeader(){
        String result = "";

        result += openWeekToken();
        result += header();
        result += closeWeekToken();

        return result;
    }

    private String header(){
        String result = " ";

        for (int day : weekLayout.header()){
            result += weekLayout.isWeekend(day) ? openWeekendToken() : openWorkdayToken();
            result += formattedShortWeekdaysForTitle(day);
            result += closeDayToken();
        }

        return result;
    }

    private String buildMonth(){
        String result = "";

        for (Week week : month){
            result += openWeekToken();
            result += writeWeek(week);
            result += closeWeekToken();
        }

        return result;
    }

    private String writeWeek(Week week) {
        String result = "";

        for (Day day : week)
            result += choiceOpenDayToken(day) + formattedDayOfMonth(day) + closeDayToken();

        return result;
    }

    private String choiceOpenDayToken(Day day){
        if (!day.isInMonth(month)) return openOtherDayToken();

        if (isCurrentDay(day)) return openTodayToken();

        if (day.isWeekend()) return openWeekendToken();

        return openWorkdayToken();
    }

    private boolean isCurrentDay(Day day){
        Calendar calendar = Calendar.getInstance();
        return (day.getDayOfMonth() == calendar.get(Calendar.DAY_OF_MONTH) &&
                day.getMonthNumber() == calendar.get(Calendar.MONTH) &&
                day.getYear() == calendar.get(Calendar.YEAR));
    }

    private String formattedDayOfMonth(Day day){
        return String.format("%4d", day.getDayOfMonth());
    }

    private String formattedShortWeekdaysForTitle(int day){
        return String.format("%s ", DateFormatSymbols.getInstance().getShortWeekdays()[day]);
    }

    protected String buildEnd(){
        return "";
    }

    private void setWeekLayout(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    protected abstract String openTitleToken();
    protected abstract String closeTitleToken();

    protected abstract String openWeekToken();
    protected abstract String closeWeekToken();

    protected abstract String openTodayToken();
    protected abstract String openWorkdayToken();
    protected abstract String openWeekendToken();
    protected abstract String openOtherDayToken();
    protected abstract String closeDayToken();
}