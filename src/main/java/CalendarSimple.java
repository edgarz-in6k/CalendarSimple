import data.WeekLayout;
import month.MonthCalendar;
import print.ConsolePrinter;
import print.HTMLPrinter;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;

public class CalendarSimple {
    public static void main(String[] args) throws IOException {
        LocalDate localDate = LocalDate.of(2015, 10, 1);
        MonthCalendar month = new MonthCalendar(localDate, WeekLayout.STANDARD);

        ConsolePrinter consolePrinter = new ConsolePrinter(month);
        consolePrinter.printToStream(System.out);

        String fileName = "calendar.html";
        HTMLPrinter htmlPrinter = new HTMLPrinter(month);
        try(PrintStream printStream = new PrintStream(new FileOutputStream(new File(fileName)))){
            htmlPrinter.printToStream(printStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Desktop.getDesktop().browse(new File(fileName).toURI());
    }
}