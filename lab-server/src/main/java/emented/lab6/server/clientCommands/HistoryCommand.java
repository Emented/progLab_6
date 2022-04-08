package emented.lab6.server.clientCommands;


import emented.lab6.common.util.DefaultMassage;
import emented.lab6.common.util.SuccessMessage;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.server.abstractions.AbstractClientCommand;

import java.util.ArrayDeque;

public class HistoryCommand extends AbstractClientCommand {

    private final ArrayDeque<String> queueOfCommands;

    public HistoryCommand(ArrayDeque<String> queueOfCommands) {
        super("history", 0, "output the last 9 commands");
        this.queueOfCommands = queueOfCommands;
    }

    @Override
    public Response executeCommand(Request request) {
        StringBuilder sb = new StringBuilder();
        if (!queueOfCommands.isEmpty()) {
            for (String name : queueOfCommands) {
                sb.append(name).append("\n");
            }
        } else {
            sb.append("History is empty");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 2));
        return new Response(new DefaultMassage(sb.toString()));
    }
}
