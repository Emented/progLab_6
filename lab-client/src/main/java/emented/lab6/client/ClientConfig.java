package emented.lab6.client;

import emented.lab6.common.util.TextPrinter;

import java.util.HashSet;
import java.util.Set;

public final class ClientConfig {
    private final static TextPrinter TEXT_PRINTER = new TextPrinter(System.out);
    private final static Set<String> historyOfScripts = new HashSet<>();

    public static TextPrinter getTextPrinter() {
        return TEXT_PRINTER;
    }

    public static Set<String> getHistoryOfScripts() {
        return historyOfScripts;
    }
}
