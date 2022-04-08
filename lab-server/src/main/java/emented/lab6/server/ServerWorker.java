package emented.lab6.server;

import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.clientCommands.*;
import emented.lab6.server.serverCommands.ServerExitCommand;
import emented.lab6.server.serverCommands.ServerHelpCommand;
import emented.lab6.server.serverCommands.ServerSaveCommand;
import emented.lab6.server.util.CommandManager;
import emented.lab6.server.util.ServerSocketWorker;

import java.io.IOException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerWorker {

    private ServerSocketWorker serverSocketWorker;
    private String fileName;
    private final Scanner scanner = new Scanner(System.in);
    private boolean statusOfRunning = true;

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
                    new ServerExitCommand(),
                    new ServerSaveCommand(ServerConfig.getCollectionOfMusicBands(), ServerConfig.getParser())));
            inputPort();
            while (statusOfRunning) {
                try {
                    Request acceptedRequest = serverSocketWorker.listenForRequest();
                    Response responseToSend = ServerConfig.getCommandManager().executeCommandToResponse(acceptedRequest);
                    serverSocketWorker.sendResponse(responseToSend);
                } catch (ClassNotFoundException e) {
                    ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText("An error occurred while deserializing the request, try again"));
                }
            }
        } catch (IOException e) {
            ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
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
                    if (portInt > 0 && portInt <= 65535) {
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
