package emented.lab6.common.util;

import java.io.PrintStream;

public class TextPrinter {

    private PrintStream printStream;

    public TextPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printText(String text) {
        printStream.print(text);
    }

    public void printlnText(String text) {
        printStream.println(text);
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }
}
