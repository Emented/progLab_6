package emented.lab6.server;

import emented.lab6.common.util.CommandHistory;
import emented.lab6.common.util.TextPrinter;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.abstractions.AbstractServerCommand;
import emented.lab6.server.parser.XMLParser;
import emented.lab6.server.util.CollectionManager;
import emented.lab6.server.util.CommandManager;

import java.util.HashMap;

public final class ServerConfig {

    private static final TextPrinter TEXT_PRINTER = new TextPrinter(System.out);
    private static final HashMap<String, AbstractClientCommand> CLIENT_AVAILABLE_COMMANDS = new HashMap<>();
    private static final HashMap<String, AbstractServerCommand> SERVER_AVAILABLE_COMMANDS = new HashMap<>();
    private static final CommandHistory CLIENT_COMMAND_HISTORY = new CommandHistory();
    private static boolean isRunning = true;

    private ServerConfig() {
    }

    public static boolean getRunning() {
        return isRunning;
    }

    public static void toggleRun() {
        isRunning = !isRunning;
    }

    public static TextPrinter getTextPrinter() {
        return TEXT_PRINTER;
    }

    public static HashMap<String, AbstractClientCommand> getClientAvailableCommands() {
        return CLIENT_AVAILABLE_COMMANDS;
    }

    public static HashMap<String, AbstractServerCommand> getServerAvailableCommands() {
        return SERVER_AVAILABLE_COMMANDS;
    }

    public static CommandHistory getClientCommandHistory() {
        return CLIENT_COMMAND_HISTORY;
    }
}
