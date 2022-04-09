package emented.lab6.client;

import emented.lab6.common.util.TextPrinter;

import java.util.HashSet;
import java.util.Set;

public final class ClientConfig {
    private static final TextPrinter TEXT_PRINTER = new TextPrinter(System.out);
    private static final Set<String> HISTORY_OF_SCRIPTS = new HashSet<>();

    private ClientConfig() {
    }

    public static TextPrinter getTextPrinter() {
        return TEXT_PRINTER;
    }

    public static Set<String> getHistoryOfScripts() {
        return HISTORY_OF_SCRIPTS;
    }
}
