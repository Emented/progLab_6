package emented.lab6.server.serverCommands;

import emented.lab6.common.util.TextColoring;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.abstractions.AbstractServerCommand;

import java.util.HashMap;

public class ServerHelpCommand extends AbstractServerCommand {

    private final HashMap<String, AbstractServerCommand> availableCommands;

    public ServerHelpCommand(HashMap<String, AbstractServerCommand> availableCommands) {
        super("help", "output help for available commands");
        this.availableCommands = availableCommands;
    }

    @Override
    public String executeCommand() {
        StringBuilder sb = new StringBuilder();
        for (AbstractServerCommand command : availableCommands.values()) {
            sb.append(command.toString()).append("\n");
        }
        return TextColoring.getGreenText("Available commands: " + sb);
    }
}
