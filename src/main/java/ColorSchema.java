public enum ColorSchema{
    DARK_ASCII(
            Color.GREEN.colorASCII,
            Color.RED.colorASCII,
            Color.BLACK.colorASCII,
            Color.BACKGROUND.colorASCII,
            Color.RESET.colorASCII
    ),
    LIGHT_ASCII(
            Color.GREEN.colorASCII,
            Color.RED.colorASCII,
            Color.WHITE.colorASCII,
            Color.BACKGROUND.colorASCII,
            Color.RESET.colorASCII
    ),
    DARK_HTML(
            Color.GREEN.colorHTML,
            Color.RED.colorHTML,
            Color.BLACK.colorHTML,
            Color.BACKGROUND.colorHTML,
            Color.RESET.colorHTML
    ),
    LIGHT_HTML(
            Color.GREEN.colorHTML,
            Color.RED.colorHTML,
            Color.WHITE.colorHTML,
            Color.BACKGROUND.colorHTML,
            Color.RESET.colorHTML
    ),
    BLANK(
            Color.NOTHING.colorASCII,
            Color.NOTHING.colorASCII,
            Color.NOTHING.colorASCII,
            Color.NOTHING.colorASCII,
            Color.NOTHING.colorASCII
    );

    String COLOR_WEEKDAY;
    String COLOR_HOLIDAY;
    String COLOR_OTHER;
    String COLOR_TODAY;
    String COLOR_RESET;

    ColorSchema(String COLOR_WEEKDAY, String COLOR_HOLIDAY, String COLOR_OTHER, String COLOR_TODAY, String COLOR_RESET) {
        this.COLOR_WEEKDAY = COLOR_WEEKDAY;
        this.COLOR_HOLIDAY = COLOR_HOLIDAY;
        this.COLOR_OTHER = COLOR_OTHER;
        this.COLOR_TODAY = COLOR_TODAY;
        this.COLOR_RESET = COLOR_RESET;
    }
}