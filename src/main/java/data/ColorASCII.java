package data;

public enum ColorASCII {
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    BACKGROUND("\u001b[44m"),
    RESET("\u001B[0m");

    public String color;

    ColorASCII(String color) {
        this.color = color;
    }
}