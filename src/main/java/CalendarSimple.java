import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CalendarSimple {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar(WeekLayout.AMERICAN);

        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.output(myCalendar);

        String fileName = "calendar.html";
        HTMLOutput htmlOutput = new HTMLOutput(fileName);
        htmlOutput.output(myCalendar);
        try {
            Desktop.getDesktop().browse(new File(fileName).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}