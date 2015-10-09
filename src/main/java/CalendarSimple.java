import java.awt.*;
import java.io.IOException;

public class CalendarSimple {
    public static void main(String[] args) throws IOException {

        MyCalendar al = new MyCalendar(WeekLayout.STANDARD);
        al.println();
        //al.generateHTMLFile("calendar.html");
//        Desktop.getDesktop().browse("calendar.html");

        System.out.println();
    }
}