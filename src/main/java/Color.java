public enum Color{
    RESET("\u001B[0m", "</comment>"),
    BLACK("\u001B[30m", "<comment style=\"color: black\">"),
    RED("\u001B[31m", "<comment style=\"color: red\">"),
    GREEN("\u001B[32m", "<comment style=\"color: green\">"),
    YELLOW("\u001B[33m", "<comment style=\"color: yellow\">"),
    BLUE("\u001B[34m", "<comment style=\"color: blue\">"),
    PURPLE("\u001B[35m", "<comment style=\"color: purple\">"),
    CYAN("\u001B[36m", "<comment style=\"color: cyan\">"),
    WHITE("\u001B[37m", "<comment style=\"color: white\">"),
    BACKGROUND("\u001b[44m", "<comment style=\"color: white; background-color: blue\">"),
    NOTHING("", "");

    String colorASCII;
    String colorHTML;

    Color(String colorASCII, String colorHTML) {
        this.colorASCII = colorASCII;
        this.colorHTML = colorHTML;
    }
}