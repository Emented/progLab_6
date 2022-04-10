package emented.lab6.server;

import emented.lab6.server.ServerConfig;
import emented.lab6.server.util.CollectionManager;
import emented.lab6.server.util.CommandManager;
import emented.lab6.server.util.ServerCommandListener;

import java.util.Scanner;

public class ConsoleThread extends Thread {

    private final ServerCommandListener serverCommandListener;
    private final CommandManager commandManager;

    public ConsoleThread(ServerCommandListener serverCommandListener, CommandManager commandManager) {
        this.serverCommandListener = serverCommandListener;
        this.commandManager = commandManager;
    }

    @Override
    public void run() {
        while (ServerConfig.getRunning()) {
            String command = serverCommandListener.readCommand();
            ServerConfig.getTextPrinter().printlnText(commandManager.executeServerCommand(command));
        }
    }

}
