public class ConsoleOutput extends PrinterMyCalendar {

    private final String newLine = "\n";
    private final String none = "";

    @Override
    protected String beginParagraphToken() {
        return none;
    }

    @Override
    protected String endParagraphToken() {
        return newLine;
    }

    @Override
    protected String beginRowToken() {
        return none;
    }

    @Override
    protected String endRowToken() {
        return newLine;
    }

    @Override
    protected String beginMonthTodayToken() {
        return ColorASCII.BACKGROUND.color;
    }

    @Override
    protected String beginWeekMonthDayToken() {
        return ColorASCII.GREEN.color;
    }

    @Override
    protected String beginHolidayMonthDayToken() {
        return ColorASCII.RED.color;
    }

    @Override
    protected String endToken() {
        return ColorASCII.RESET.color;
    }

    @Override
    protected String beginOtherMonthDayToken() {
        return ColorASCII.BLACK.color;
    }

    @Override
    protected void formattedOutput(String s) {
        System.out.println(s);
    }
}