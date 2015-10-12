public class ConsoleOutput extends PrinterMyCalendar {

    @Override
    protected String beginParagraph() {
        return "";
    }

    @Override
    protected String endParagraph() {
        return "\n";
    }

    @Override
    protected String beginRow() {
        return "";
    }

    @Override
    protected String endRow() {
        return "\n";
    }

    @Override
    protected String beginItemToday() {
        return ColorASCII.BACKGROUND.color;
    }

    @Override
    protected String beginItemWeekday() {
        return ColorASCII.GREEN.color;
    }

    @Override
    protected String beginItemHoliday() {
        return ColorASCII.RED.color;
    }

    @Override
    protected String endItem() {
        return ColorASCII.RESET.color;
    }

    @Override
    protected String beginItemAnotherDay() {
        return ColorASCII.BLACK.color;
    }

    @Override
    protected void formattedOutput(String s) {
        System.out.println(s);
    }
}