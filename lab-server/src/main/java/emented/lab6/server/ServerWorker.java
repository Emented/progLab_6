package emented.lab6.server;

import com.thoughtworks.xstream.converters.ConversionException;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.clientCommands.AddCommand;
import emented.lab6.server.clientCommands.AddIfMaxCommand;
import emented.lab6.server.clientCommands.ClearCommand;
import emented.lab6.server.clientCommands.CountLessThatNumberOfParticipantsCommand;
import emented.lab6.server.clientCommands.ExecuteScriptCommand;
import emented.lab6.server.clientCommands.ExitCommand;
import emented.lab6.server.clientCommands.HelpCommand;
import emented.lab6.server.clientCommands.HistoryCommand;
import emented.lab6.server.clientCommands.InfoCommand;
import emented.lab6.server.clientCommands.MinByStudioCommand;
import emented.lab6.server.clientCommands.RemoveAnyByNumberOfParticipantsCommand;
import emented.lab6.server.clientCommands.RemoveByIdCommand;
import emented.lab6.server.clientCommands.RemoveGreaterCommand;
import emented.lab6.server.clientCommands.ShowCommand;
import emented.lab6.server.clientCommands.UpdateCommand;
import emented.lab6.server.parser.XMLParser;
import emented.lab6.server.serverCommands.ServerExitCommand;
import emented.lab6.server.serverCommands.ServerHelpCommand;
import emented.lab6.server.serverCommands.ServerHistoryCommand;
import emented.lab6.server.serverCommands.ServerSaveCommand;
import emented.lab6.server.util.CollectionManager;
import emented.lab6.server.util.CommandManager;
import emented.lab6.server.util.ServerCommandListener;
import emented.lab6.server.util.ServerSocketWorker;

import java.io.IOException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerWorker {

    private final Scanner scanner = new Scanner(System.in);
    private final int maxPort = 65535;
    private ServerSocketWorker serverSocketWorker;
    private final String fileName;
    private final ServerCommandListener serverCommandListener = new ServerCommandListener(scanner);
    private CollectionManager collectionManager;
    private CommandManager commandManager;
    private final XMLParser parser = new XMLParser();


    public ServerWorker(String fileName) {
        this.fileName = fileName;
    }

    public void startServerWorker() {
        try {
            collectionManager = parser.readFromXML(this.fileName);
            commandManager = new CommandManager(
                    new HelpCommand(ServerConfig.getClientAvailableCommands()),
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new AddCommand(collectionManager),
                    new UpdateCommand(collectionManager),
                    new RemoveByIdCommand(collectionManager),
                    new ClearCommand(collectionManager),
                    new ExitCommand(),
                    new AddIfMaxCommand(collectionManager),
                    new RemoveGreaterCommand(collectionManager),
                    new HistoryCommand(ServerConfig.getClientCommandHistory().getHistory()),
                    new RemoveAnyByNumberOfParticipantsCommand(collectionManager),
                    new MinByStudioCommand(collectionManager),
                    new CountLessThatNumberOfParticipantsCommand(collectionManager),
                    new ExecuteScriptCommand(),
                    new ServerHelpCommand(ServerConfig.getServerAvailableCommands()),
                    new ServerExitCommand(scanner, parser, collectionManager, serverSocketWorker),
                    new ServerSaveCommand(collectionManager, parser),
                    new ServerHistoryCommand(ServerConfig.getClientCommandHistory().getHistory()));
            inputPort();
            ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getGreenText("Welcome to the server! To see the list of commands input HELP"));
            RequestThread requestThread = new RequestThread(serverSocketWorker, commandManager);
            ConsoleThread consoleThread = new ConsoleThread(serverCommandListener, commandManager);
            requestThread.start();
            consoleThread.start();
        } catch (IOException e) {
            ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
        } catch (ConversionException e) {
            ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText("Error during type conversion"));
            System.exit(1);
        }
    }

    private void inputPort() throws IOException {
        ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getGreenText("Do you want to use a default port? [y/n]"));
        try {
            String s = scanner.nextLine().strip().toLowerCase(Locale.ROOT);
            if ("n".equals(s)) {
                ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getGreenText("Please enter the remote host port (1-65535)"));
                String port = scanner.nextLine();
                try {
                    int portInt = Integer.parseInt(port);
                    if (portInt > 0 && portInt <= maxPort) {
                        serverSocketWorker = new ServerSocketWorker(portInt);
                    } else {
                        ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText("The number did not fall within the limits, repeat the input"));
                        inputPort();
                    }
                } catch (IllegalArgumentException e) {
                    ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText("Error processing the number, repeat the input"));
                    inputPort();
                }
            } else if ("y".equals(s)) {
                serverSocketWorker = new ServerSocketWorker();
            } else {
                ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText("You entered not valid symbol, try again"));
                inputPort();
            }
        } catch (NoSuchElementException e) {
            ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText("An invalid character has been entered, forced shutdown!"));
            System.exit(1);
        }
    }
}
