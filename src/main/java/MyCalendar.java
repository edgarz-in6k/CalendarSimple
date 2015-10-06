import java.util.Calendar;

import static java.lang.System.out;

public class MyCalendar {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String colorWeekday = ANSI_GREEN;
    private static final String colorHoliday = ANSI_RED;
    private static final String colorOther = ANSI_WHITE;
    private static final String colorToday = ANSI_BLUE;
    private static final String colorReset = ANSI_RESET;

    private static final int DAY_OF_WEEK_SIZE = 7;
    private static final int WEEK_OF_MASS_SIZE = 6;
    private static final int[][] day = new int[WEEK_OF_MASS_SIZE][DAY_OF_WEEK_SIZE];

    private int amountDayInMonth;
    private int amountDayInMonthPrev;
    private int today;
    private int startIdxThisMonth;
    private int holiday1;
    private int holiday2;
    private String headString;

    private Calendar calendar;
    private Style style;

    public enum Style{
        STANDARD, AMERICAN
    }

    public MyCalendar(Style style) {
        initCalendar();
        setStyle(style);
        makeBuild();
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        if (style == Style.STANDARD){
            startIdxThisMonth = calendar.get(Calendar.DAY_OF_WEEK);
            holiday1 = 5;
            holiday2 = 6;
            headString = " " + colorWeekday + "Mon  Thu  Wed  Tue  Fri  " + colorHoliday + "Sat  Sun" + colorReset;
        }
        else{
            startIdxThisMonth = calendar.get(Calendar.DAY_OF_WEEK) + 1;
            holiday1 = 0;
            holiday2 = 0;
            headString = " " + colorHoliday + "Sun  " + colorWeekday + "Mon  Thu  Wed  Tue  Fri  Sat" + colorReset;
        }
        makeBuild();
    }

    private void initCalendar(){
        calendar = (Calendar)Calendar.getInstance().clone();
        calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);

        amountDayInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        today = calendar.get(Calendar.DAY_OF_MONTH) + 1;

        calendar.add(Calendar.MONTH, 1);
        amountDayInMonthPrev = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private void makeBuild(){
        int minusCountDayInNextMonth = 0;
        boolean isMinusCountDayInNextMonth = false;
        int k = 1 - startIdxThisMonth;
        for (int i=0; i< WEEK_OF_MASS_SIZE; i++){
            for (int j=0; j< DAY_OF_WEEK_SIZE; j++){

                day[i][j] = k++ - minusCountDayInNextMonth;

                if (!isMinusCountDayInNextMonth && k> amountDayInMonth){
                    minusCountDayInNextMonth = amountDayInMonth;
                    isMinusCountDayInNextMonth = true;
                }

            }
        }

        k = 0;
        for (int i=startIdxThisMonth-1; i>=0; i--)
            day[0][i] = amountDayInMonthPrev - k++;
    }

    public void print(){
        System.out.print(this);
    }

    public void println(){
        System.out.println(this);
    }

    @Override
    public String toString(){
        String s = "";
        s += headString + "\n";
        int flagColorMonth = 0;// 0 1 2
        s += colorOther;
        for (int i=0; i< WEEK_OF_MASS_SIZE; i++){
            for (int j=0; j< DAY_OF_WEEK_SIZE; j++){
                if (day[i][j]==1){
                    flagColorMonth++;
                    if (flagColorMonth==1)
                        s += colorWeekday;
                    else if (flagColorMonth==2)
                        s += colorOther;
                }

                if (j==holiday1 || j==holiday2){
                    if (flagColorMonth==0 || flagColorMonth==2){
                        s += String.format("%4d ", day[i][j]);
                    }
                    else{
                        s += colorHoliday;
                        s += String.format("%4d ", day[i][j]);
                        s += colorWeekday;
                    }

                }
                else if (day[i][j]==today && flagColorMonth==1){
                    s += colorToday;
                    s += String.format("%4d ", day[i][j]);
                    s += colorWeekday;
                }
                else
                    s += String.format("%4d ", day[i][j]);
            }
            s += "\n";
        }
        s += colorReset;
        return s;
    }

}