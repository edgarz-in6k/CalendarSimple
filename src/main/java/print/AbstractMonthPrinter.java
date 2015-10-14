package print;

import data.WeekLayout;
import month.DayCalendar;
import month.MonthCalendar;
import month.WeekCalendar;

import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public abstract class AbstractMonthPrinter {

    public static final String COMMA_AND_SPACE = ", ";
    protected MonthCalendar month;
    protected WeekLayout weekLayout;

    public AbstractMonthPrinter(MonthCalendar month){
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
                month.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) +
                COMMA_AND_SPACE +
                month.getYear() +
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
        String result = "";

        for (DayOfWeek day : weekLayout.header()){
            result += weekLayout.isWeekend(day) ? openWeekendToken() : openWorkdayToken();
            result += formattedShortWeekdaysForTitle(day);
            result += closeDayToken();
        }

        return result;
    }

    private String buildMonth(){
        String result = "";

        for (WeekCalendar week : month){
            result += openWeekToken();
            result += writeWeek(week);
            result += closeWeekToken();
        }

        return result;
    }

    private String writeWeek(WeekCalendar week) {
        String result = "";

        for (DayCalendar day : week)
            result += choiceOpenDayToken(day) + formattedDayOfMonth(day) + closeDayToken();

        return result;
    }

    private String choiceOpenDayToken(DayCalendar day){
        if (!day.isInMonth(month)) return openOtherDayToken();

        if (isCurrentDay(day)) return openTodayToken();

        if (day.isWeekend()) return openWeekendToken();

        return openWorkdayToken();
    }

    private boolean isCurrentDay(DayCalendar day){
        LocalDate tempLocalDate = LocalDate.now();
        return (day.getDayOfMonth() == tempLocalDate.getDayOfMonth() &&
                day.getMonthNumber() == tempLocalDate.getMonth() &&
                day.getYear() == tempLocalDate.getYear());
    }

    private String formattedDayOfMonth(DayCalendar day){
        return String.format("%4d", day.getDayOfMonth());
    }

    private String formattedShortWeekdaysForTitle(DayOfWeek day){
        return String.format("%4s", day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
    }

    protected String buildEnd(){
        return "";
    }

    private void setWeekLayout(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
    }

    public void setMonth(MonthCalendar month) {
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