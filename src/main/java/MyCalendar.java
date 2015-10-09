import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyCalendar {

    private static final int WEEKS_COUNT = 6;
    private static final int WEEK_SIZE = 7;
    private static final int[][] monthCalendar = new int[WEEKS_COUNT][WEEK_SIZE];

    private int amountOfDaysInMonth;
    private int today;
    private int firstDayOfMonthIndex;

    private Calendar calendar = Calendar.getInstance();
    private ColorSchema colorSchema = ColorSchema.DARK_ASCII;
    private WeekLayout weekLayout = WeekLayout.STANDARD;

    public MyCalendar() {
        this(WeekLayout.STANDARD);
    }

    public MyCalendar(WeekLayout weekLayout) {
        this(weekLayout, ColorSchema.DARK_ASCII);
    }

    public MyCalendar(WeekLayout weekLayout, ColorSchema colorSchema) {
        this.colorSchema = colorSchema;
        this.weekLayout = weekLayout;
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

    public ColorSchema getColorSchema() {
        return colorSchema;
    }

    public void setColorSchema(ColorSchema colorSchema) {
        this.colorSchema = colorSchema;
    }

    private void init() {
        int dayOfWeekIndex = getDayOfMondayBasedWeekBeginWithZero();
        firstDayOfMonthIndex = dayOfWeekIndex + weekLayout.offset();

        amountOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        today = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private int getDayOfMondayBasedWeekBeginWithZero() {
        DateFormat dateFormat = new SimpleDateFormat("u");
        calendar.getActualMinimum(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        int result = Integer.parseInt(dateFormat.format(calendar.getTime()));
        calendar = Calendar.getInstance();
        return result;
    }

    private void buildCalendar() {
        rollbackCalendarToFirstDayOfFirstWeek();
        for (int week = 0; week < WEEKS_COUNT; week++) {
            for (int weekday = 0; weekday < WEEK_SIZE; weekday++) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                monthCalendar[week][weekday] = calendar.get(Calendar.DAY_OF_MONTH);
            }
        }
        calendar = Calendar.getInstance();
    }

    private void rollbackCalendarToFirstDayOfFirstWeek() {
        calendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonthIndex - today);
    }

    public void print() {
        System.out.print(this);
    }

    public void println() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        String s = "";
        s += calendarHeader();
        s += generateCalendar();
        return s;
    }

    private String calendarHeader() {
        String s = "";
        DateFormat dateFormat = new SimpleDateFormat("MMMM Y");
        s += dateFormat.format(calendar.getTime()) + colorSchema.END_PARAGRAPH;
        return s;
    }

    private String formattedThisDay(int i, int j) {
        int day = monthCalendar[i][j];
        return formatDay(day);
    }

    private String formatDay(int day) {
        return String.format("%4d ", day);
    }

    public void generateHTMLFile(String path) throws IOException {
        ColorSchema tempColorSchema = getColorSchema();
        setColorSchema(ColorSchema.DARK_HTML);

        File file = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                //"\t\t<h1>" +  + "</h1>\n" +
                generateTableForHTML() +
                "\t</body>\n" +
                "</html>");
        bw.close();

        setColorSchema(tempColorSchema);
    }

    String generateTableForHTML(){
        String s = colorSchema.START_HEAD + calendarHeader() + colorSchema.END_HEAD;
        s += "<table cellpadding=\"5\" border=\"1\">";
        s += generateCalendar();
        s += "</table>";
        return s;
    }

    String generateCalendar(){
        String s = "";
        s += weekLayout.header(colorSchema) + colorSchema.END_PARAGRAPH;
        for (int week=0; week<WEEKS_COUNT; week++){
            s += colorSchema.START_PARAGRAPH;
            for (int weekday=0; weekday<WEEK_SIZE; weekday++){
                if (isDayOfOther(week, weekday))
                    s += colorSchema.COLOR_OTHER;
                else if (isDayOfToday(week, weekday))
                    s += colorSchema.COLOR_TODAY;
                else if (weekLayout.isHoliday(week,weekday))
                    s += colorSchema.COLOR_HOLIDAY;
                else
                    s += colorSchema.COLOR_WEEKDAY;
                s += formattedThisDay(week, weekday) + colorSchema.COLOR_RESET;
            }
            s += colorSchema.END_PARAGRAPH;
        }
        return s;
    }

    boolean isDayOfOther(int week, int weekday){
        return (weekday < firstDayOfMonthIndex && week == 0) || (week >3 && monthCalendar[week][weekday]<14);
    }

    boolean isDayOfToday(int week, int weekday){
        return today == monthCalendar[week][weekday];
    }

}