package emented.lab6.server.serverCommands;

import emented.lab6.common.util.TextColoring;
import emented.lab6.server.abstractions.AbstractServerCommand;

import java.util.ArrayDeque;

public class ServerHistoryCommand extends AbstractServerCommand {

    private final ArrayDeque<String> queueOfCommands;

    public ServerHistoryCommand(ArrayDeque<String> queueOfCommands) {
        super("history", "output the last 9 commands");
        this.queueOfCommands = queueOfCommands;
    }

    @Override
    public String executeServerCommand() {
        StringBuilder sb = new StringBuilder();
        if (!queueOfCommands.isEmpty()) {
            for (String name : queueOfCommands) {
                sb.append(name).append("\n");
            }
        } else {
            return TextColoring.getGreenText("History is empty");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        return sb.toString();
    }
}
