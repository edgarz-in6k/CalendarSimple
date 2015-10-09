import java.io.IOException;

public class CalendarSimple {
    public static void main(String[] args) throws IOException {

        MyCalendar al = new MyCalendar(WeekLayout.AMERICAN);
        al.println();
        al.generateHTMLFile("calendar.html");

        System.out.println();
    }
}