package emented.lab6.server;

import emented.lab6.server.ServerConfig;
import emented.lab6.server.util.ServerCommandListener;

import java.util.Scanner;

public class ConsoleThread extends Thread {

    private final ServerCommandListener serverCommandListener;

    public ConsoleThread(ServerCommandListener serverCommandListener) {
        this.serverCommandListener = serverCommandListener;
    }

    @Override
    public void run() {
        while (ServerConfig.getRunning()) {
            String command = serverCommandListener.readCommand();
            ServerConfig.getTextPrinter().printlnText(ServerConfig.getCommandManager().executeServerCommand(command));
        }
    }

}
