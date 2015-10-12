import month.Month;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class CalendarSimple {
    public static void main(String[] args) {
        /*MyCalendar myCalendar = new MyCalendar(WeekLayout.STANDARD);

        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.output(myCalendar);

        String fileName = "calendar.html";
        HTMLOutput htmlOutput = new HTMLOutput(fileName);
        htmlOutput.output(myCalendar);
        try {
            Desktop.getDesktop().browse(new File(fileName).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Month month = new Month(Calendar.getInstance());
    }
}