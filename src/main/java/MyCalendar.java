import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyCalendar {

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
    private static ColorSchema colorSchema = ColorSchema.DARK;
    private static WeekLayout weekLayout = WeekLayout.STANDARD;

    enum ColorSchema{
        DARK(
                Color.ANSI_GREEN,
                Color.ANSI_RED,
                Color.ANSI_WHITE,
                Color.ANSI_BACKGROUND,
                Color.ANSI_RESET
        ),
        LIGHT(
                Color.ANSI_GREEN,
                Color.ANSI_RED,
                Color.ANSI_WHITE,
                Color.ANSI_BACKGROUND,
                Color.ANSI_RESET
        );
        String COLOR_WEEKDAY;
        String COLOR_HOLIDAY;
        String COLOR_OTHER;
        String COLOR_TODAY;
        String COLOR_RESET;

        ColorSchema(Color COLOR_WEEKDAY, Color COLOR_HOLIDAY, Color COLOR_OTHER, Color COLOR_TODAY, Color COLOR_RESET) {
            this.COLOR_WEEKDAY = COLOR_WEEKDAY.color;
            this.COLOR_HOLIDAY = COLOR_HOLIDAY.color;
            this.COLOR_OTHER = COLOR_OTHER.color;
            this.COLOR_TODAY = COLOR_TODAY.color;
            this.COLOR_RESET = COLOR_RESET.color;
        }
    }
    enum Color{
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

        Color(String color) {
            this.color = color;
        }
    }

    public enum WeekLayout {
        STANDARD(
                0,
                Day.SATURDAY.value,
                Day.SUNDAY.value,
                " " + colorSchema.COLOR_WEEKDAY + "Mon  Thu  Wed  Tue  Fri  " + colorSchema.COLOR_HOLIDAY + "Sat  Sun" + colorSchema.COLOR_RESET
        ),
        AMERICAN(
                1,
                Day.MONDAY.value,
                Day.MONDAY.value,
                " " + colorSchema.COLOR_HOLIDAY + "Sun  " + colorSchema.COLOR_WEEKDAY + "Mon  Thu  Wed  Tue  Fri  Sat" + colorSchema.COLOR_RESET
        );

        private final int offsetForAmerican;
        private final int firstHolidayIndex;
        private final int secondHolidayIndex;
        private final String header;

        WeekLayout(int offsetForAmerican, int firstHolidayIndex, int secondHolidayIndex, String header) {
            this.offsetForAmerican = offsetForAmerican;
            this.firstHolidayIndex = firstHolidayIndex;
            this.secondHolidayIndex = secondHolidayIndex;
            this.header = header;
        }
    }

    enum Day{
        MONDAY(0, Calendar.MONDAY),
        TUESDAY(1, Calendar.THURSDAY),
        WEDNESDAY(2, Calendar.WEDNESDAY),
        THURSDAY(3, Calendar.THURSDAY),
        FRIDAY(4, Calendar.FRIDAY),
        SATURDAY(5, Calendar.SATURDAY),
        SUNDAY(6, Calendar.SUNDAY);
        Day(int value, int cal) {
            this.value = value;
            this.cal = cal;
        }

        private int value;
        private int cal;
    }

    public MyCalendar() {
        init();
        buildCalendar();
    }

    public MyCalendar(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
        init();
        buildCalendar();
    }

    public MyCalendar(ColorSchema colorSchema) {
        this.colorSchema = colorSchema;
        init();
        buildCalendar();
    }

    public WeekLayout getWeekLayout() {
        return weekLayout;
    }

    public void setWeekLayout(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
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
        //calendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeekIndex = getZeroMondayBasedDayOfWeek();
        firstDayOfMonthIndex = dayOfWeekIndex + weekLayout.offsetForAmerican;

        System.out.println(dayOfWeekIndex);

        amountOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        today = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, -1);
        amountOfDaysInMonthPrev = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.MONTH, 1);
    }

    //restruct
    private int getZeroMondayBasedDayOfWeek() {
        DateFormat dateFormat = new SimpleDateFormat("u");
        return Integer.parseInt(dateFormat.format(calendar.getTime())) - 1;
    }

    private void buildCalendar(){
        int k = 1 - firstDayOfMonthIndex;
        for (int i=0; i<WEEKS_COUNT; i++)
            for (int j=0; j<WEEK_SIZE; j++)
                monthCalendar[i][j] = k++;

        correctionMonthPrev();
        correctionMonthNext();


        /*for (int i=0; i<WEEKS_COUNT; i++){
            for (int j=0; j<WEEK_SIZE; j++) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                System.out.format("%4d", calendar.get(Calendar.DAY_OF_MONTH));
            }
            System.out.println();
        }*/

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

    public void simplePrintlnASCII(){
        for (int i=0; i< WEEKS_COUNT; i++){
            for (int j=0; j< WEEK_SIZE; j++){
                System.out.printf("%4d", monthCalendar[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public String toString(){
        return headerCalendarASCII() + beginCalendarASCII() + baseCalendarASCII() + endCalendarASCII();
    }

    private String headerCalendarASCII(){
        String s = "";
        DateFormat dateFormat = new SimpleDateFormat("MMMM, Y");
        s += dateFormat.format(calendar.getTime()) + "\n";
        s += weekLayout.header + "\n";
        s += colorSchema.COLOR_RESET;
        return s;
    }

    private String beginCalendarASCII(){
        String s = "";
        s += colorSchema.COLOR_OTHER;
        for (int j=0; j<firstDayOfMonthIndex; j++)
            s += String.format("%4d ", monthCalendar[0][j]);
        s += colorSchema.COLOR_RESET;
        return s;
    }
//rename
    private String baseCalendarASCII(){
        int startIndexI = 0;
        int startIndexJ = firstDayOfMonthIndex;

        String s = "";
        s += colorSchema.COLOR_WEEKDAY;
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
        s += colorSchema.COLOR_RESET;
        return s;
    }

    private String choiceColorForThisDay(int i, int j){
        if (monthCalendar[i][j] == today && numberMonthRelativeThis == 0)
            return colorSchema.COLOR_TODAY;
        else if (j == weekLayout.firstHolidayIndex || j == weekLayout.secondHolidayIndex)
            return colorSchema.COLOR_HOLIDAY;
        else
            return colorSchema.COLOR_WEEKDAY;
    }

    private String formattedThisDay(int i, int j){
        return String.format("%4d ", monthCalendar[i][j]);
    }

    private String endCalendarASCII(){
        int lastNumberInCalendar = monthCalendar[WEEKS_COUNT-1][WEEK_SIZE-1];

        int startIndexI = WEEK_SIZE - (lastNumberInCalendar / WEEK_SIZE + 1) - 1;
        int startIndexJ = WEEK_SIZE - lastNumberInCalendar % WEEK_SIZE;

        String s = "";
        s += colorSchema.COLOR_OTHER;
        for (int i=startIndexI; i< WEEKS_COUNT; i++){
            for (int j=startIndexJ; j< WEEK_SIZE; j++){
                s += formattedThisDay(i, j);
            }
            s += "\n";
            startIndexJ = 0;
        }
        s += colorSchema.COLOR_RESET;
        return s;
    }

    public void generateHTMLFile(String path) throws IOException {
        File file = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<h1>head</h1>\n" +
                "\n" +
                "\t\t<p>body</p>\n" +
                "\t</body>\n" +
                "</html>");
        bw.close();
        Desktop.getDesktop().browse(file.toURI());
    }

}