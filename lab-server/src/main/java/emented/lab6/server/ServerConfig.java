package emented.lab6.server;

import emented.lab6.common.util.CommandHistory;
import emented.lab6.common.util.TextPrinter;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.abstractions.AbstractServerCommand;
import emented.lab6.server.clientCommands.*;
import emented.lab6.server.parser.XMLParser;
import emented.lab6.server.serverCommands.ServerExitCommand;
import emented.lab6.server.serverCommands.ServerHelpCommand;
import emented.lab6.server.serverCommands.ServerSaveCommand;
import emented.lab6.server.util.CollectionManager;
import emented.lab6.server.util.CommandManager;

import java.util.HashMap;

public final class ServerConfig {

    private static final TextPrinter TEXT_PRINTER = new TextPrinter(System.out);
    private static final HashMap<String, AbstractClientCommand> CLIENT_AVAILABLE_COMMANDS = new HashMap<>();
    private static final HashMap<String, AbstractServerCommand> SERVER_AVAILABLE_COMMANDS = new HashMap<>();
    private static final CommandHistory clientCommandHistory = new CommandHistory();
    private static final XMLParser parser = new XMLParser();
    private static CollectionManager collectionOfMusicBands = new CollectionManager();
    private static CommandManager commandManager;

    public static void setCollectionOfMusicBands(CollectionManager manager) {
        collectionOfMusicBands = manager;
    }

    public static CollectionManager getCollectionOfMusicBands() {
        return collectionOfMusicBands;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    public static void setCommandManager(CommandManager manager) {
        commandManager = manager;
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
        return clientCommandHistory;
    }

    public static XMLParser getParser() {
        return parser;
    }
}
