package emented.lab6.server.clientCommands;

import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.server.abstractions.AbstractClientCommand;

import java.util.HashSet;
import java.util.Set;

public class ExecuteScriptCommand extends AbstractClientCommand {

    private static final Set<String> FILE_HISTORY = new HashSet<>();

    public ExecuteScriptCommand() {
        super("execute_script", 1, "read and execute the script from the specified file", "file name");
    }

    @Override
    public Response executeClientCommand(Request request) {
        return null;
    }
}
