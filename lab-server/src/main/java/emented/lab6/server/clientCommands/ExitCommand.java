package emented.lab6.server.clientCommands;

import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.server.abstractions.AbstractClientCommand;

public class ExitCommand extends AbstractClientCommand {

    public ExitCommand() {
        super("exit", 0, "shut down the client (all your changes will be lost)");
    }

    @Override
    public Response executeCommand(Request request) {
        return null;
    }
}
