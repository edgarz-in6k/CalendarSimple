import data.WeekLayout;
import month.MyCalendar;
import print.ConsoleOutput;
import print.HTMLOutput;

public class CalendarSimple {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar(WeekLayout.STANDARD);

        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.output(myCalendar);

        String fileName = "calendar.html";
        HTMLOutput htmlOutput = new HTMLOutput(fileName);
        htmlOutput.output(myCalendar);
        /*try {
            Desktop.getDesktop().browse(new File(fileName).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*month.Month month = myCalendar.getMonth();
        for (Week w : month){
            for (Day day : w){
                System.out.print(day.getDayOfMonth() + " ");
            }
            System.out.println();
        }*/
    }
}