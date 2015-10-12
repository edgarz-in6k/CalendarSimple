import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLOutput extends PrinterMyCalendar{

    private String path;

    HTMLOutput(String path){
        this.path = path;
    }

    @Override
    protected String startOutput(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                nameMonthOutput() +
                "\t\t<table cellpadding=\"5\" cellspacing=\"0\" border=\"1\">\n";
    }

    private String nameMonthOutput(){
        return super.startOutput();
    }

    @Override
    protected String beginItemToday() {
        return "\t\t\t\t<td style=\"color: white; background-color: blue\">";
    }

    @Override
    protected String beginItemWeekday() {
        return "\t\t\t\t<td style=\"color: green\">";
    }

    @Override
    protected String beginItemHoliday() {
        return "\t\t\t\t<td style=\"color: red\">";
    }

    @Override
    protected String beginItemAnotherDay() {
        return "\t\t\t\t<td style=\"color: black\">";
    }

    @Override
    protected String endItem() {
        return "</td>\n";
    }

    @Override
    protected String endOutput(){
        return "\t\t</table>\n" +
                "\t</body>\n" +
                "</html>";
    }

    @Override
    protected String beginParagraph() {
        return "\t\t\t<p style=\"font-weight: bold\">";
    }

    @Override
    protected String endParagraph() {
        return "</p>\n";
    }

    @Override
    protected String beginRow() {
        return "\n\t\t\t<tr>\n";
    }

    @Override
    protected String endRow() {
        return "\t\t\t</tr>\n";
    }

    @Override
    protected void formattedOutput(String s) {
        File file = new File(path);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}