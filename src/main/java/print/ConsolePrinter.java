package print;

import data.ColorASCII;
import month.Month;

public class ConsolePrinter extends AbstractMonthPrinter {

    private static final String newLine = "\n";
    private static final String none = "";

    public ConsolePrinter(Month month) {
        super(month);
    }

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
    protected String openTodayToken() {
        return ColorASCII.BACKGROUND.color;
    }

    @Override
    protected String openWorkdayToken() {
        return ColorASCII.GREEN.color;
    }

    @Override
    protected String openWeekendToken() {
        return ColorASCII.RED.color;
    }

    @Override
    protected String closeDayToken() {
        return ColorASCII.RESET.color;
    }

    @Override
    protected String openOtherDayToken() {
        return ColorASCII.BLACK.color;
    }
}