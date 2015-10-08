import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyCalendar {

    private static final String COLOR_WEEKDAY = ColorCalendar.ANSI_GREEN.color;
    private static final String COLOR_HOLIDAY = ColorCalendar.ANSI_RED.color;
    private static final String COLOR_OTHER = ColorCalendar.ANSI_WHITE.color;
    private static final String COLOR_TODAY = ColorCalendar.ANSI_BACKGROUND.color;
    private static final String COLOR_RESET = ColorCalendar.ANSI_RESET.color;

    private int OFFSET_FOR_TRANSFORM_IN_INDEX = 1;
    private static final int WEEKS_COUNT = 6;
    private static final int WEEK_SIZE = 7;
    private static final int[][] monthCalendar = new int[WEEKS_COUNT][WEEK_SIZE];

    private int amountOfDaysInMonth;
    private int amountOfDaysInMonthPrev;
    private int today;
    private int firstDayOfMonthIndex;

    private Calendar calendar = Calendar.getInstance();
    private int numberMonthRelativeThis = 0;
    private Style style;

    enum ColorCalendar{
        ANSI_RESET("\u001B[0m"),
        ANSI_BLACK("\u001B[30m"),
        ANSI_RED("\u001B[31m"),
        ANSI_GREEN("\u001B[32m"),
        ANSI_YELLOW("\u001B[33m"),
        ANSI_BLUE("\u001B[34m"),
        ANSI_PURPLE("\u001B[35m"),
        ANSI_CYAN("\u001B[36m"),
        ANSI_WHITE("\u001B[37m"),
        ANSI_BACKGROUND("\u001b[44m");

        String color;

        ColorCalendar(String color) {
            this.color = color;
        }
    }

    public enum Style{
        STANDARD(
                0,
                Day.SATURDAY.value,
                Day.SUNDAY.value,
                " " + COLOR_WEEKDAY + "Mon  Thu  Wed  Tue  Fri  " + COLOR_HOLIDAY + "Sat  Sun" + COLOR_RESET
        ),
        AMERICAN(
                1,
                Day.MONDAY.value,
                Day.MONDAY.value,
                " " + COLOR_HOLIDAY + "Sun  " + COLOR_WEEKDAY + "Mon  Thu  Wed  Tue  Fri  Sat" + COLOR_RESET
        );

        private final int offsetForAmerican;
        private final int firstHolidayIndex;
        private final int secondHolidayIndex;
        private final String header;

        Style(int offsetForAmerican, int firstHolidayIndex, int secondHolidayIndex, String header) {
            this.offsetForAmerican = offsetForAmerican;
            this.firstHolidayIndex = firstHolidayIndex;
            this.secondHolidayIndex = secondHolidayIndex;
            this.header = header;
        }
    }

    enum Day{
        MONDAY(0), TUESDAY(1), WEDNESDAY(2), THURSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(6);
        private int value;
        Day(int value) {
            this.value = value;
        }
    }

    public MyCalendar(Style style) {
        this.style = style;
        init();
        buildCalendar();
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
        buildCalendar();
    }

    public void nextMonth(){
        addMonth();
        init();
        buildCalendar();
    }

    private void addMonth(){
        calendar.add(Calendar.MONTH,1);
        numberMonthRelativeThis++;
    }

    public void prevMonth(){
        subMonth();
        init();
        buildCalendar();
    }

    private void subMonth(){
        calendar.add(Calendar.MONTH,-1);
        numberMonthRelativeThis--;
    }

    private void init(){
        DateFormat dateFormat = new SimpleDateFormat("u");
        int dayOfWeekIndex = Integer.parseInt(dateFormat.format(calendar.getTime())) - 1;
        firstDayOfMonthIndex = dayOfWeekIndex + style.offsetForAmerican;

        amountOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        dateFormat = new SimpleDateFormat("dd");
        today = Integer.parseInt(dateFormat.format(calendar.getTime()));

        calendar.add(Calendar.MONTH, -1);
        amountOfDaysInMonthPrev = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.MONTH, 1);
    }

    private void buildCalendar(){
        int k = 1 - firstDayOfMonthIndex;
        for (int i=0; i<WEEKS_COUNT; i++)
            for (int j=0; j<WEEK_SIZE; j++)
                monthCalendar[i][j] = k++;

        correctionMonthPrev();
        correctionMonthNext();
    }

    private void correctionMonthPrev(){
        for (int j= firstDayOfMonthIndex -1; j>=0; j--)
            monthCalendar[0][j] += amountOfDaysInMonthPrev;
    }

    private void correctionMonthNext(){
        for (int i= WEEKS_COUNT -1; i>=0; i--){
            for (int j= WEEK_SIZE -1; j>=0; j--){
                if (monthCalendar[i][j] == amountOfDaysInMonth)
                    return;
                monthCalendar[i][j] -= amountOfDaysInMonth;
            }
        }
    }

    public void print(){
        System.out.print(this);
    }

    public void println(){
        System.out.println(this);
    }

    public void simplePrintlnASCI(){
        for (int i=0; i< WEEKS_COUNT; i++){
            for (int j=0; j< WEEK_SIZE; j++){
                System.out.printf("%4d", monthCalendar[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public String toString(){
        return headerCalendarASCI() + beginCalendarASCI() + baseCalendarASCI() + endCalendarASCI();
    }

    private String headerCalendarASCI(){
        String s = "";
        DateFormat dateFormat = new SimpleDateFormat("MMMM, Y");
        s += dateFormat.format(calendar.getTime()) + "\n";
        s += style.header + "\n";
        s += COLOR_RESET;
        return s;
    }

    private String beginCalendarASCI(){
        String s = COLOR_OTHER;
        for (int j=0; j<firstDayOfMonthIndex; j++)
            s += String.format("%4d ", monthCalendar[0][j]);
        s += COLOR_RESET;
        return s;
    }

    private String baseCalendarASCI(){
        int startIndexI = 0;
        int startIndexJ = firstDayOfMonthIndex;

        String s = COLOR_WEEKDAY;
        for (int i=startIndexI; i< WEEKS_COUNT; i++){
            for (int j=startIndexJ; j< WEEK_SIZE; j++){

                s += choiceColorForThisDay(i,j);
                s += formattedThisDay(i, j);

                if (monthCalendar[i][j] == amountOfDaysInMonth){// && i != startIndexI
                    if (j == WEEK_SIZE-1)
                        s += "\n";
                    return s;
                }
            }
            s += "\n";
            startIndexJ = 0;
        }
        s += COLOR_RESET;
        return s;
    }

    private String choiceColorForThisDay(int i, int j){
        if (monthCalendar[i][j] == today && numberMonthRelativeThis == 0)
            return COLOR_TODAY;
        else if (j == style.firstHolidayIndex || j == style.secondHolidayIndex)
            return COLOR_HOLIDAY;
        else
            return COLOR_WEEKDAY;
    }

    private String formattedThisDay(int i, int j){
        return String.format("%4d ", monthCalendar[i][j]);
    }

    private String endCalendarASCI(){
        int lastNumberInCalendar = monthCalendar[WEEKS_COUNT-1][WEEK_SIZE-1];

        int startIndexI = WEEK_SIZE - (lastNumberInCalendar / WEEK_SIZE + 1) - 1;
        int startIndexJ = WEEK_SIZE - lastNumberInCalendar % WEEK_SIZE;

        String s = COLOR_OTHER;
        for (int i=startIndexI; i< WEEKS_COUNT; i++){
            for (int j=startIndexJ; j< WEEK_SIZE; j++){
                s += formattedThisDay(i, j);
            }
            s += "\n";
            startIndexJ = 0;
        }
        s += COLOR_RESET;
        return s;
    }

}