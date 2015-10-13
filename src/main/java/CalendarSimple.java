import data.WeekLayout;
import month.Month;
import print.ConsolePrinter;
import print.HTMLPrinter;

import java.awt.*;
import java.io.*;
import java.util.Calendar;

public class CalendarSimple {
    public static void main(String[] args) throws IOException {
        Month month = new Month(Calendar.getInstance(), WeekLayout.AMERICAN);

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

/*month.Month month = myCalendar.getMonth();
        for (Week w : month){
            for (Day day : w){
                System.out.print(day.getDayOfMonth() + " ");
            }
            System.out.println();
        }*/
//private String[] namesDay = {" Mon", " Thu", " Wed", " Tue", " Fri", " Sat", " Sun"};
//int[] nameDays = {Calendar.MONDAY, Calendar.THURSDAY, Calendar.WEDNESDAY, Calendar.TUESDAY, Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY};