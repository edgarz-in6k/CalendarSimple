import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyCalendar {

    private Calendar calendar;
    public static final int WEEKS_COUNT = 6;
    public static final int WEEK_SIZE = 7;
    private final int[][] monthCalendar = new int[WEEKS_COUNT][WEEK_SIZE];

    //private int today;
    private int firstDayOfMonthIndex;
    int amountOfDaysInMonth;
    private int today;

    private WeekLayout weekLayout;

    public MyCalendar(){
        this(WeekLayout.STANDARD);
    }

    public MyCalendar(WeekLayout weekLayout){
        setWeekLayout(weekLayout);
        setCalendar();
        init();
        buildCalendar();
    }

    private void init(){
        int dayOfWeekIndex = getDayOfWeekInNumberFormatBeginWithZero();
        firstDayOfMonthIndex = dayOfWeekIndex + weekLayout.offset;
        amountOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        today = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private int getDayOfWeekInNumberFormatBeginWithZero(){
        DateFormat dateFormat = new SimpleDateFormat("u");
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int result = Integer.parseInt(dateFormat.format(calendar.getTime())) - 1;
        setCalendar();
        return result;
    }

    private void buildCalendar(){
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -(firstDayOfMonthIndex + 1));
        for (int i=0; i<WEEKS_COUNT; i++){
            for (int j=0; j<WEEK_SIZE; j++) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                monthCalendar[i][j] = calendar.get(Calendar.DAY_OF_MONTH);
            }
        }
        setCalendar();
    }

    public void setCalendar() {
        calendar = Calendar.getInstance();
        //calendar.set(Calendar.MONTH, 4);////////////////////
        calendar.add(Calendar.MONTH, 1);////////////////////
        //calendar.add(Calendar.MONTH, -1);////////////////////
    }

    public int getFirstDayOfMonthIndex() {
        return firstDayOfMonthIndex;
    }

    public int getAmountOfDaysInMonth() {
        return amountOfDaysInMonth;
    }

    public int getToday() {
        return today;
    }

    public int[][] getMonthCalendar() {
        return monthCalendar;
    }

    public WeekLayout getWeekLayout() {
        return weekLayout;
    }

    public void setWeekLayout(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
    }

    public void printSimple(){
        for (int i=0; i<WEEKS_COUNT; i++){
            for (int j=0; j<WEEK_SIZE; j++) {
                System.out.printf("%4d", monthCalendar[i][j]);
            }
            System.out.println();
        }
    }
}