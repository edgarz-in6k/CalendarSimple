package print;

import month.Month;

public class HTMLPrinter extends AbstractMonthPrinter {

    private static final String TAB = "\t";
    private static final String TAB_TWO = TAB + TAB;
    private static final String TAB_THREE = TAB_TWO + TAB;
    private static final String TAB_FOUR = TAB_THREE + TAB;
    private static final String FONT_WEIGHT = TAB_FOUR + "<p style=\" font-weight: bold\">";
    private static final String NEW_LINE = "\n";
    private static final String CLOSE_TD = "</td>" + NEW_LINE;
    private static final String CLOSE_P = "</p>" + NEW_LINE;
    private static final String OPEN_TR = NEW_LINE + TAB_THREE + "<tr>" + NEW_LINE;
    private static final String CLOSE_TR = TAB_THREE + "</tr>" + NEW_LINE;
    private static final String TABLE_BODY_HTML = TAB_TWO + "</table>" + NEW_LINE + TAB + "</body>" + NEW_LINE + "</html>";
    private static final String DOCTYPE_HTML_BODY = "<!DOCTYPE html>" + NEW_LINE + "<html>" + NEW_LINE + TAB + "<body>" + NEW_LINE;
    private static final String TABLE_CELLPADDING_CELLSPACING_BORDER = TAB_TWO + "<table cellpadding=\"5\" cellspacing=\"0\" border=\"1\">" + NEW_LINE;
    private static final String TD_STYLE_COLOR = "<td style=\"color: ";
    private static final String CLOSE_TEG = "\">";

    private String path = "calendar.html";

    public HTMLPrinter(Month month){
        super(month);
    }

    @Override
    protected String buildBegin(){
        return DOCTYPE_HTML_BODY;
    }

    @Override
    protected String buildTitle(){
        return super.buildTitle() + TABLE_CELLPADDING_CELLSPACING_BORDER;
    }

    @Override
    protected String openTodayToken() {
        return getStyle("white; background-color: blue");
    }

    @Override
    protected String openWorkdayToken() {
        return getStyle("green");
    }

    @Override
    protected String openWeekendToken() {
        return getStyle("red");
    }

    @Override
    protected String openOtherDayToken() {
        return getStyle("black");
    }

    private String getStyle(String color) {
        return TAB_FOUR + TD_STYLE_COLOR + color + CLOSE_TEG;
    }

    @Override
    protected String closeDayToken() {
        return CLOSE_TD;
    }

    @Override
    protected String buildEnd(){
        return TABLE_BODY_HTML;
    }

    @Override
    protected String openTitleToken() {
        return FONT_WEIGHT;
    }

    @Override
    protected String closeTitleToken() {
        return CLOSE_P;
    }

    @Override
    protected String openWeekToken() {
        return OPEN_TR;
    }

    @Override
    protected String closeWeekToken() {
        return CLOSE_TR;
    }

    private void setPath(String path) {
        this.path = path;
    }
}