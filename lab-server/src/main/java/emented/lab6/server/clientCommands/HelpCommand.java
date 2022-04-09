package emented.lab6.server.clientCommands;

import emented.lab6.common.util.DefaultMassage;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.abstractions.AbstractClientCommand;

import java.util.HashMap;

public class HelpCommand extends AbstractClientCommand {

    private final HashMap<String, AbstractClientCommand> availableCommands;

    public HelpCommand(HashMap<String, AbstractClientCommand> availableCommands) {
        super("help", 0, "show list available commands");
        this.availableCommands = availableCommands;
    }

    @Override
    public Response executeClientCommand(Request request) {
        StringBuilder sb = new StringBuilder();
        for (AbstractClientCommand command : availableCommands.values()) {
            sb.append(command.toString()).append("\n");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        return new Response(new DefaultMassage(TextColoring.getGreenText("Available commands:\n") + sb));
    }
}
