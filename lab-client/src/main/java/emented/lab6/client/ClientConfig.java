package emented.lab6.client;

import emented.lab6.common.abstractions.AbstractTextPrinter;
import emented.lab6.common.util.ConsoleTextPrinter;

import java.util.HashSet;
import java.util.Set;

public final class ClientConfig {
    private static final AbstractTextPrinter CONSOLE_TEXT_PRINTER = new ConsoleTextPrinter();
    private static final Set<String> HISTORY_OF_SCRIPTS = new HashSet<>();

    private ClientConfig() {
    }

    public static AbstractTextPrinter getConsoleTextPrinter() {
        return CONSOLE_TEXT_PRINTER;
    }

    public static Set<String> getHistoryOfScripts() {
        return HISTORY_OF_SCRIPTS;
    }
}
