package emented.lab6.server.serverCommands;

import emented.lab6.common.util.TextColoring;
import emented.lab6.server.ServerConfig;
import emented.lab6.server.abstractions.AbstractServerCommand;

public class ServerExitCommand extends AbstractServerCommand {

    public ServerExitCommand() {
        super("exit", "shut down the server (you'll be asked to store all the changes)");
    }

    @Override
    public String executeCommand() {
        ServerConfig.toggleRun();
        return TextColoring.getGreenText("Server shutdown");
    }
}
