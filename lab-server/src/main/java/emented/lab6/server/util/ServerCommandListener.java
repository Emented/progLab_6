package emented.lab6.server.util;

import emented.lab6.common.util.TextColoring;
import emented.lab6.server.ServerConfig;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerCommandListener {

    private final Scanner sc;

    public ServerCommandListener(Scanner sc) {
        this.sc = sc;
    }

    public String readCommand() {
        try {
            ServerConfig.getTextPrinter().printText(TextColoring.getBlueText("Enter a command: "));
            return sc.nextLine().toLowerCase(Locale.ROOT);
        } catch (NoSuchElementException e) {
            ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText("An invalid character has been entered, forced shutdown!"));
            System.exit(1);
        }
        return TextColoring.getRedText("An error occurred");
    }
}
