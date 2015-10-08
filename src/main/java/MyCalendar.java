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
    private ColorSchema colorSchema = ColorSchema.DARK;
    private WeekLayout weekLayout = WeekLayout.STANDARD;

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
        firstDayOfMonthIndex = dayOfWeekIndex + weekLayout.offsetForAmerican();

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
        calendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonthIndex - today);
        for (int i=0; i<WEEKS_COUNT; i++){
            for (int j=0; j<WEEK_SIZE; j++) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                //System.out.format("%4d", calendar.get(Calendar.DAY_OF_MONTH));
                monthCalendar[i][j] = calendar.get(Calendar.DAY_OF_MONTH);
            }
        }
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, numberMonthRelativeThis);
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
        return outCalendarAllASCII();
    }

    private String outCalendarAllASCII(){
        String s = "";
        s += outCalendarHeaderASCII();
        s += outCalendarMonthPrevASCII();
        s += outCalendarMonthThisASCII();
        s += outCalendarMonthNextASCII();
        return s;
    }

    private String outCalendarHeaderASCII(){
        String s = "";
        DateFormat dateFormat = new SimpleDateFormat("MMMM, Y");
        s += dateFormat.format(calendar.getTime()) + "\n";
        s += weekLayout.header(colorSchema) + "\n";
        s += colorSchema.COLOR_RESET;
        return s;
    }

    private String outCalendarMonthPrevASCII(){
        String s = "";
        s += colorSchema.COLOR_OTHER;
        for (int j=0; j<firstDayOfMonthIndex; j++)
            s += String.format("%4d ", monthCalendar[0][j]);
        return s;
    }

    private String outCalendarMonthThisASCII(){
        int startIndexI = 0;
        int startIndexJ = firstDayOfMonthIndex;

        String s = "";
        s += colorSchema.COLOR_WEEKDAY;
        for (int i=startIndexI; i< WEEKS_COUNT; i++){
            for (int j=startIndexJ; j< WEEK_SIZE; j++){

                s += choiceColorForThisDay(i,j);
                s += formattedThisDay(i, j);

                if (monthCalendar[i][j] == amountOfDaysInMonth){// && i != startIndexI
                    /*if (j == WEEK_SIZE-1)
                        s += "\n";*/
                    return s;
                }
            }
            s += "\n";
            startIndexJ = 0;
        }
        return s;
    }

    private String choiceColorForThisDay(int i, int j){
        if (monthCalendar[i][j] == today && numberMonthRelativeThis == 0)
            return colorSchema.COLOR_TODAY;
        else if (j == weekLayout.firstHolidayIndex() || j == weekLayout.secondHolidayIndex())
            return colorSchema.COLOR_HOLIDAY;
        else
            return colorSchema.COLOR_WEEKDAY;
    }

    private String formattedThisDay(int i, int j){
        return String.format("%4d ", monthCalendar[i][j]);
    }

    private String outCalendarMonthNextASCII(){
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