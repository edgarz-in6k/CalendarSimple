public enum Color{
    BLACK("\u001B[30m", "<td style=\"color: black\">"),
    RED("\u001B[31m", "<td style=\"color: red\">"),
    GREEN("\u001B[32m", "<td style=\"color: green\">"),
    YELLOW("\u001B[33m", "<td style=\"color: yellow\">"),
    BLUE("\u001B[34m", "<td style=\"color: blue\">"),
    PURPLE("\u001B[35m", "<td style=\"color: purple\">"),
    CYAN("\u001B[36m", "<td style=\"color: cyan\">"),
    WHITE("\u001B[37m", "<td style=\"color: white\">"),
    BACKGROUND("\u001b[44m", "<td style=\"color: white; background-color: blue\">"),

    START_PARAGRAPH("", "<tr>"),
    END_PARAGRAPH("\n", "</tr>"),
    START_HEAD("", "<p>"),
    END_HEAD("", "</p>"),

    RESET("\u001B[0m", "</td>");

    String colorASCII;
    String colorHTML;

    Color(String colorASCII, String colorHTML) {
        this.colorASCII = colorASCII;
        this.colorHTML = colorHTML;
    }
}