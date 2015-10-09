import java.awt.*;
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
        s += calendarPreviousMonth();
        s += calendarCurrentMonth();
        s += calendarNextMonth();
        return s;
    }

    private String calendarHeader() {
        String s = "";
        DateFormat dateFormat = new SimpleDateFormat("MMMM Y");
        s += dateFormat.format(calendar.getTime()) + "\n";
        s += weekLayout.header(colorSchema) + "\n";
        return s;
    }

    private String calendarPreviousMonth() {
        int[] firstWeek = monthCalendar[0];
        String s = "";
        s += colorSchema.COLOR_OTHER;
        for (int weekday = 0; firstWeek[weekday] > 1; weekday++) {
            s += formatDay(firstWeek[weekday]);
        }
        s += colorSchema.COLOR_RESET;
        return s;
    }

    private String calendarCurrentMonth() {
        int indexFirstDayOfMonth = firstDayOfMonthIndex;

        String s = "";
        s += colorSchema.COLOR_WEEKDAY;
        for (int i = 0; i < WEEKS_COUNT; i++) {
            for (int j = indexFirstDayOfMonth; j < WEEK_SIZE; j++) {

                s += choiceColorForThisDay(i, j);
                s += formattedThisDay(i, j);
                s += colorSchema.COLOR_RESET;

                if (monthCalendar[i][j] == amountOfDaysInMonth) {
                    return s;
                }
            }
            s += "\n";
            indexFirstDayOfMonth = 0;
        }
        return s;
    }

    private String choiceColorForThisDay(int i, int j) {
        //System.out.println(i + " " + j);
        if (monthCalendar[i][j] == today)
            return colorSchema.COLOR_TODAY;
        else if (j == weekLayout.firstHolidayIndex() || j == weekLayout.secondHolidayIndex())
            return colorSchema.COLOR_HOLIDAY;
        else
            return colorSchema.COLOR_WEEKDAY;
    }

    private String formattedThisDay(int i, int j) {
        int day = monthCalendar[i][j];
        return formatDay(day);
    }

    private String formatDay(int day) {
        return String.format("%4d ", day);
    }

    private String calendarNextMonth() {
        int lastNumberInCalendar = monthCalendar[WEEKS_COUNT - 1][WEEK_SIZE - 1];

        int startIndexI = WEEK_SIZE - (lastNumberInCalendar / WEEK_SIZE + 1) - 1;
        int startIndexJ = WEEK_SIZE - lastNumberInCalendar % WEEK_SIZE;

        String s = "";
        s += colorSchema.COLOR_OTHER;
        for (int i = startIndexI; i < WEEKS_COUNT; i++) {
            for (int j = startIndexJ; j < WEEK_SIZE; j++) {
                s += formattedThisDay(i, j);
            }
            s += "\n";
            startIndexJ = 0;
        }
        s += colorSchema.COLOR_RESET;
        return s;
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
                "<pre>" +
                toString() +
                "</pre>" +
                "\t</body>\n" +
                "</html>");
        bw.close();

        setColorSchema(tempColorSchema);
    }

}