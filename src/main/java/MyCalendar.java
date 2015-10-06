import java.util.Calendar;

import static java.lang.System.out;

public class MyCalendar {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final String colorWeekday = ANSI_GREEN;
    private static final String colorHoliday = ANSI_RED;
    private static final String colorOther = ANSI_WHITE;
    private static final String colorToday = ANSI_BLUE;

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

    public enum Style{
        STANDARD, AMERICAN
    }

    public MyCalendar(Style style) {
        out.println("October, 2015 year");

        Calendar calendar = (Calendar)Calendar.getInstance().clone();
        calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);

        amountDayInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        today = calendar.get(Calendar.DAY_OF_MONTH) + 1;

        calendar.add(Calendar.MONTH, 1);
        amountDayInMonthPrev = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //out.println(amountDayInMonthPrev);

        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        if (style == Style.STANDARD){
            startIdxThisMonth = calendar.get(Calendar.DAY_OF_WEEK);
            holiday1 = 5;
            holiday2 = 6;
            headString = " " + colorWeekday + "Mon  Thu  Wed  Tue  Fri  " + colorHoliday + "Sat  Sun" + ANSI_RESET;
        }
        else{
            startIdxThisMonth = calendar.get(Calendar.DAY_OF_WEEK) + 1;
            holiday1 = 0;
            holiday2 = 0;
            headString = " " + colorHoliday + "Sun  " + colorWeekday + "Mon  Thu  Wed  Tue  Fri  Sat" + ANSI_RESET;
        }

        makeBuild();
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
        out.println(headString);
        int flagColorMonth = 0;// 0 1 2
        out.print(colorOther);
        for (int i=0; i< WEEK_OF_MASS_SIZE; i++){
            for (int j=0; j< DAY_OF_WEEK_SIZE; j++){
                if (day[i][j]==1){
                    flagColorMonth++;
                    if (flagColorMonth==1)
                        out.print(colorWeekday);
                    else if (flagColorMonth==2)
                        out.print(colorOther);
                }

                if (j==holiday1 || j==holiday2){
                    if (flagColorMonth==0 || flagColorMonth==2){
                        out.format("%4d ", day[i][j]);
                    }
                    else{
                        out.print(colorHoliday);
                        out.format("%4d ", day[i][j]);
                        out.print(colorWeekday);
                    }

                }
                else if (day[i][j]==today && flagColorMonth==1){
                    out.print(colorToday);
                    out.format("%4d ", day[i][j]);
                    out.print(colorWeekday);
                }
                else
                    out.format("%4d ", day[i][j]);
            }
            out.println();
        }
        out.print(ANSI_RESET);
    }

}