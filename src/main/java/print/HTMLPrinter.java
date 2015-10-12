package print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLPrinter extends MonthPrinter {

    private String path;

    public HTMLPrinter(String path){
        this.path = path;
    }

    @Override
    protected String startWrite(){
        return headHTML(namesMonthsOutput());
    }

    private String headHTML(String namesMonths){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                namesMonths +
                "\t\t<table cellpadding=\"5\" cellspacing=\"0\" border=\"1\">\n";
    }

    private String namesMonthsOutput(){
        return super.startWrite();
    }

    @Override
    protected String openMonthTodayToken() {
        return getStyle("white; background-color: blue");
    }

    @Override
    protected String openWeekMonthDayToken() {
        return getStyle("green");
    }

    @Override
    protected String openHolidayMonthDayToken() {
        return getStyle("red");
    }

    @Override
    protected String openOtherMonthDayToken() {
        return getStyle("black");
    }

    private String getStyle(String color) {
        return "\t\t\t\t<td style=\"color: " + color + "\">";
    }

    @Override
    protected String closeDayToken() {
        return "</td>\n";
    }

    @Override
    protected String endWrite(){
        return "\t\t</table>\n" +
                "\t</body>\n" +
                "</html>";
    }

    @Override
    protected String openParagraphToken() {
        return "\t\t\t\t<p style=\" font-weight: bold\">";
    }

    @Override
    protected String closeParagraphToken() {
        return "</p>\n";
    }

    @Override
    protected String openWeekToken() {
        return "\n\t\t\t<tr>\n";
    }

    @Override
    protected String closeWeekToken() {
        return "\t\t\t</tr>\n";
    }

    @Override
    protected void outputToStream(String s) {
        File file = new File(path);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}