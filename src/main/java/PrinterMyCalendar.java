import java.awt.*;

public abstract class PrinterMyCalendar {

    private MyCalendar myCalendar;
    private Point pointStopOutput = new Point(0,0);
    private int[][] monthCalendar;

    private WeekLayout weekLayout;

    protected String startOutput(){
        return "";
    }

    protected String headerOutput(){
        String s = "";
        s += beginParagraph();
        s += weekLayout.header(this);
        s += endParagraph();
        return s;
    }

    protected String mainOutput(){
        String s = outputMonthPrev();
        s += outputMonthThis();
        s += outputMonthNext();
        return s;
    }

    protected String outputMonthPrev(){
        String s = "";
        s += beginParagraph();
        for (int j=0; j<myCalendar.getFirstDayOfMonthIndex(); j++)
            s += beginItemAnotherDay() + formattedThisDay(0,j) + endItem();
        return s;
    }

    protected String outputMonthThis(){
        int indexFirstDayOfMonth = myCalendar.getFirstDayOfMonthIndex();

        String s = "";
        for (int i=0; i< MyCalendar.WEEKS_COUNT; i++){
            if (indexFirstDayOfMonth == 0)
                s += beginParagraph();
            for (int j=indexFirstDayOfMonth; j< MyCalendar.WEEK_SIZE; j++){

                s += choiceColorForThisDay(i,j) + formattedThisDay(i,j) + endItem();

                if (isEndMonth(i, j)){
                    pointStopOutput.setLocation(i,j+1);
                    return s;
                }
            }
            s += endParagraph();
            indexFirstDayOfMonth = 0;
            pointStopOutput.setLocation(i,0);
        }
        return s;
    }

    private boolean isEndMonth(int i, int j) {
        return (monthCalendar[i][j] == myCalendar.getAmountOfDaysInMonth());
    }

    private String choiceColorForThisDay(int i, int j) {
        if (isToday(i,j))
            return beginItemToday();
        else if (isHoliday(i,j))
            return beginItemHoliday();
        else
            return beginItemWeekday();
    }

    protected boolean isToday(int i, int j){
        return (monthCalendar[i][j] == myCalendar.getToday());// && numberMonthRelativeThis == 0)
    }

    protected boolean isHoliday(int i, int j){
        return (j == weekLayout.firstHolidayIndex || j == weekLayout.secondHolidayIndex);
    }

    protected String outputMonthNext(){
        String s = "";
        int startIndexWEEKS_COUNT = pointStopOutput.x;
        int startIndexWEEK_SIZE = pointStopOutput.y;
        for (int i=startIndexWEEKS_COUNT; i< MyCalendar.WEEKS_COUNT; i++){
            for (int j=startIndexWEEK_SIZE; j< MyCalendar.WEEK_SIZE; j++){
                s += beginItemAnotherDay() + formattedThisDay(i,j) + endItem();
            }
            s += endParagraph();
            startIndexWEEK_SIZE = 0;
        }
        return s;
    }

    protected String formattedThisDay(int i, int j){
        return String.format("%4d", monthCalendar[i][j]);
    }

    protected String endOutput(){
        return "";
    }

    public void setMyCalendar(MyCalendar myCalendar) {
        this.myCalendar = myCalendar;
    }

    public void setMonthCalendar(int[][] monthCalendar) {
        this.monthCalendar = monthCalendar;
    }

    public void setWeekLayout(WeekLayout weekLayout) {
        this.weekLayout = weekLayout;
    }

    public void print(MyCalendar myCalendar){
        setMyCalendar(myCalendar);
        setMonthCalendar(myCalendar.getMonthCalendar());
        setWeekLayout(myCalendar.getWeekLayout());
        String s = startOutput();
        s += headerOutput();
        s += mainOutput();
        s += endOutput();
        output(s);
    }

    protected abstract String beginParagraph();
    protected abstract String endParagraph();
    protected abstract String beginItemToday();
    protected abstract String beginItemWeekday();
    protected abstract String beginItemHoliday();
    protected abstract String endItem();
    protected abstract String beginItemAnotherDay();

    protected abstract void output(String s);
}

/*
    startOutput
    headerOutput
    mainOutput
        outputMonthPrev
        outputMonthThis
        outputMonthNext
    endOutput
*/