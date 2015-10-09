import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CalendarSimple {
    public static void main(String[] args) throws IOException {

        MyCalendar al = new MyCalendar(WeekLayout.AMERICAN);
        al.println();
        al.generateHTMLFile("calendar.html");
        Desktop.getDesktop().browse(new File("calendar.html").toURI());

        //new PrinterMyCalendar(al);

        System.out.println();
    }
}