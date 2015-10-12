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
    protected String beginMonthTodayToken() {
        return getStyle("blue");
    }

    @Override
    protected String beginWeekMonthDayToken() {
        return getStyle("green");
    }

    @Override
    protected String beginHolidayMonthDayToken() {
        return getStyle("red");
    }

    @Override
    protected String beginOtherMonthDayToken() {
        return getStyle("black");
    }

    private String getStyle(String color) {
        return "\t\t\t\t<td style=\"color: " + color + "\">";
    }

    @Override
    protected String endToken() {
        return "</td>\n";
    }

    @Override
    protected String endWrite(){
        return "\t\t</table>\n" +
                "\t</body>\n" +
                "</html>";
    }

    @Override
    protected String beginParagraphToken() {
        return getStyle("black font-weight: bold");
    }

    @Override
    protected String endParagraphToken() {
        return "</p>\n";
    }

    @Override
    protected String beginRowToken() {
        return "\n\t\t\t<tr>\n";
    }

    @Override
    protected String endRowToken() {
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