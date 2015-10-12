import java.awt.*;
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
                "\t<table cellpadding=\"5\" border=\"1\">\n";
    }

    @Override
    protected String beginItemToday() {
        return "<td style=\"color: white; background-color: blue\">";
    }

    @Override
    protected String beginItemWeekday() {
        return "<td style=\"color: green\">";
    }

    @Override
    protected String beginItemHoliday() {
        return "<td style=\"color: red\">";
    }

    @Override
    protected String endItem() {
        return "</td>";
    }

    @Override
    protected String beginItemAnotherDay() {
        return "<td style=\"color: black\">";
    }

    @Override
    protected String endOutput(){
        return "</table>" +
                "\t</body>\n" +
                "</html>";
    }

    @Override
    protected String beginParagraph() {
        return "<tr>";
    }

    @Override
    protected String endParagraph() {
        return "</tr>";
    }

    @Override
    protected void output(String s) {
        File file = new File(path);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}