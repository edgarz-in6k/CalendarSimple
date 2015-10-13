package print;

import data.ColorASCII;
import month.Month;

public class ConsolePrinter extends AbstractMonthPrinter {

    private static final String NEW_LINE = "\n";
    private static final String NONE = "";

    public ConsolePrinter(Month month) {
        super(month);
    }

    @Override
    protected String openTitleToken() {
        return NONE;
    }

    @Override
    protected String closeTitleToken() {
        return NEW_LINE;
    }

    @Override
    protected String openWeekToken() {
        return NONE;
    }

    @Override
    protected String closeWeekToken() {
        return NEW_LINE;
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