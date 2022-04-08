package emented.lab6.server.clientCommands;

import emented.lab6.common.util.*;
import emented.lab6.server.abstractions.AbstractClientCommand;

import java.util.HashMap;

public class HelpCommand extends AbstractClientCommand {

    private final HashMap<String, AbstractClientCommand> availableCommands;

    public HelpCommand(HashMap<String, AbstractClientCommand> availableCommands) {
        super("help", 0, "output help for available commands");
        this.availableCommands = availableCommands;
    }

    @Override
    public Response executeCommand(Request request) {
        StringBuilder sb = new StringBuilder();
        for (AbstractClientCommand command : availableCommands.values()) {
            sb.append(command.toString()).append("\n");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 2));
        return new Response(new DefaultMassage(TextColoring.getGreenText("Available commands:\n") + sb));
    }
}
