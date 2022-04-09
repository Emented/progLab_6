package emented.lab6.server;

import com.thoughtworks.xstream.converters.ConversionException;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.Threads.ConsoleThread;
import emented.lab6.server.Threads.RequestThread;
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
import emented.lab6.server.serverCommands.ServerExitCommand;
import emented.lab6.server.serverCommands.ServerHelpCommand;
import emented.lab6.server.serverCommands.ServerSaveCommand;
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
    private String fileName;
    private boolean statusOfRunning = true;
    private ServerCommandListener serverCommandListener = new ServerCommandListener(scanner);

    public ServerWorker(String fileName) {
        this.fileName = fileName;
    }

    public void toggleStatus() {
        statusOfRunning = !statusOfRunning;
    }

    public void startServerWorker() {
        try {
            ServerConfig.setCollectionOfMusicBands(ServerConfig.getParser().readFromXML(this.fileName));
            ServerConfig.setCommandManager(new CommandManager(
                    new HelpCommand(ServerConfig.getClientAvailableCommands()),
                    new InfoCommand(ServerConfig.getCollectionOfMusicBands()),
                    new ShowCommand(ServerConfig.getCollectionOfMusicBands()),
                    new AddCommand(ServerConfig.getCollectionOfMusicBands()),
                    new UpdateCommand(ServerConfig.getCollectionOfMusicBands()),
                    new RemoveByIdCommand(ServerConfig.getCollectionOfMusicBands()),
                    new ClearCommand(ServerConfig.getCollectionOfMusicBands()),
                    new ExitCommand(),
                    new AddIfMaxCommand(ServerConfig.getCollectionOfMusicBands()),
                    new RemoveGreaterCommand(ServerConfig.getCollectionOfMusicBands()),
                    new HistoryCommand(ServerConfig.getClientCommandHistory().getHistory()),
                    new RemoveAnyByNumberOfParticipantsCommand(ServerConfig.getCollectionOfMusicBands()),
                    new MinByStudioCommand(ServerConfig.getCollectionOfMusicBands()),
                    new CountLessThatNumberOfParticipantsCommand(ServerConfig.getCollectionOfMusicBands()),
                    new ExecuteScriptCommand(),
                    new ServerHelpCommand(ServerConfig.getServerAvailableCommands()),
                    new ServerExitCommand(scanner),
                    new ServerSaveCommand(ServerConfig.getCollectionOfMusicBands(), ServerConfig.getParser())));
            inputPort();
            ServerConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Welcome to the server! To see the list of commands input HELP"));
            RequestThread requestThread = new RequestThread(serverSocketWorker);
            ConsoleThread consoleThread = new ConsoleThread(scanner, serverCommandListener);
            requestThread.start();
            consoleThread.start();
        } catch (IOException e) {
            ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
        } catch (ConversionException e) {
            ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText("Error during type conversion"));
            System.exit(1);
        }
    }

    private void inputPort() throws IOException {
        ServerConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Do you want to use a default port? [y/n]"));
        try {
            String s = scanner.nextLine().toLowerCase(Locale.ROOT);
            if ("n".equals(s)) {
                ServerConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Please enter the remote host port (1-65535)"));
                String port = scanner.nextLine();
                try {
                    int portInt = Integer.parseInt(port);
                    if (portInt > 0 && portInt <= maxPort) {
                        serverSocketWorker = new ServerSocketWorker(portInt);
                    } else {
                        ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText("The number did not fall within the limits, repeat the input"));
                        inputPort();
                    }
                } catch (IllegalArgumentException e) {
                    ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText("Error processing the number, repeat the input"));
                    inputPort();
                }
            } else if ("y".equals(s)) {
                serverSocketWorker = new ServerSocketWorker();
            } else {
                ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText("You entered not valid symbol, try again"));
                inputPort();
            }
        } catch (NoSuchElementException e) {
            ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText("An invalid character has been entered, forced shutdown!"));
            System.exit(1);
        }
    }
}
