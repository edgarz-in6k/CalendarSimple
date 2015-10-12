public class ConsoleOutput extends PrinterMyCalendar {

    private final String newLine = "\n";
    private final String none = "";

    @Override
    protected String openParagraphToken() {
        return none;
    }

    @Override
    protected String closeParagraphToken() {
        return newLine;
    }

    @Override
    protected String openWeekToken() {
        return none;
    }

    @Override
    protected String closeWeekToken() {
        return newLine;
    }

    @Override
    protected String openMonthTodayToken() {
        return ColorASCII.BACKGROUND.color;
    }

    @Override
    protected String openWeekMonthDayToken() {
        return ColorASCII.GREEN.color;
    }

    @Override
    protected String openHolidayMonthDayToken() {
        return ColorASCII.RED.color;
    }

    @Override
    protected String closeDayToken() {
        return ColorASCII.RESET.color;
    }

    @Override
    protected String openOtherMonthDayToken() {
        return ColorASCII.BLACK.color;
    }

    @Override
    protected void formattedOutput(String s) {
        System.out.println(s);
    }
}