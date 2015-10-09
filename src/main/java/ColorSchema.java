public enum ColorSchema{
    DARK_ASCII(
            Color.GREEN.colorASCII,
            Color.RED.colorASCII,
            Color.BLACK.colorASCII,
            Color.BACKGROUND.colorASCII,
            Color.RESET.colorASCII,
            Color.START_PARAGRAPH.colorASCII,
            Color.END_PARAGRAPH.colorASCII,
            Color.START_HEAD.colorASCII,
            Color.END_HEAD.colorASCII
    ),
    LIGHT_ASCII(
            Color.GREEN.colorASCII,
            Color.RED.colorASCII,
            Color.WHITE.colorASCII,
            Color.BACKGROUND.colorASCII,
            Color.RESET.colorASCII,
            Color.START_PARAGRAPH.colorASCII,
            Color.END_PARAGRAPH.colorASCII,
            Color.START_HEAD.colorASCII,
            Color.END_HEAD.colorASCII
    ),
    DARK_HTML(
            Color.GREEN.colorHTML,
            Color.RED.colorHTML,
            Color.BLACK.colorHTML,
            Color.BACKGROUND.colorHTML,
            Color.RESET.colorHTML,
            Color.START_PARAGRAPH.colorHTML,
            Color.END_PARAGRAPH.colorHTML,
            Color.START_HEAD.colorHTML,
            Color.END_HEAD.colorHTML
    ),
    LIGHT_HTML(
            Color.GREEN.colorHTML,
            Color.RED.colorHTML,
            Color.WHITE.colorHTML,
            Color.BACKGROUND.colorHTML,
            Color.RESET.colorHTML,
            Color.START_PARAGRAPH.colorHTML,
            Color.END_PARAGRAPH.colorHTML,
            Color.START_HEAD.colorHTML,
            Color.END_HEAD.colorHTML
    );

    String COLOR_WEEKDAY;
    String COLOR_HOLIDAY;
    String COLOR_OTHER;
    String COLOR_TODAY;
    String COLOR_RESET;

    String START_PARAGRAPH;
    String END_PARAGRAPH;
    String START_HEAD;
    String END_HEAD;

    ColorSchema(String COLOR_WEEKDAY, String COLOR_HOLIDAY, String COLOR_OTHER, String COLOR_TODAY, String COLOR_RESET, String START_PARAGRAPH, String END_PARAGRAPH, String START_HEAD, String END_HEAD) {
        this.COLOR_WEEKDAY = COLOR_WEEKDAY;
        this.COLOR_HOLIDAY = COLOR_HOLIDAY;
        this.COLOR_OTHER = COLOR_OTHER;
        this.COLOR_TODAY = COLOR_TODAY;
        this.COLOR_RESET = COLOR_RESET;
        this.START_PARAGRAPH = START_PARAGRAPH;
        this.END_PARAGRAPH = END_PARAGRAPH;
        this.START_HEAD = START_HEAD;
        this.END_HEAD = END_HEAD;
    }

    String holiday(String day) {
        return colorize(day, COLOR_HOLIDAY);
    }

    String weekday(String day) {
        return colorize(day, COLOR_WEEKDAY);
    }

    String colorize(String day, String color){
        return START_HEAD + color + day + COLOR_RESET + END_HEAD;
    }
}