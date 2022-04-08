package emented.lab6.server.clientCommands;

import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.server.abstractions.AbstractClientCommand;

import java.util.HashSet;
import java.util.Set;

public class ExecuteScriptCommand extends AbstractClientCommand {

    private static final Set<String> fileHistory = new HashSet<>();

    public ExecuteScriptCommand() {
        super("execute_script", 1, "считать и исполнить скрипт из указанного файла", "имя файла");
    }

    @Override
    public Response executeCommand(Request request) {
        //TODO realize
//        try {
//            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
//            if (fileHistory.contains(commandArgs[0])) {
//                OutputUtil.printErrorMessage("Возможно зацикливание");
//            } else {
//                fileHistory.add(commandArgs[0]);
//                reader.readCommandsFromFile(commandArgs[0]);
//                ArrayList<String> commands = reader.getCommandsFromFile();
//                if (commands.contains("execute_script " + commandArgs[0])) {
//                    throw new LoopPossibilityException("Внутри скрипта найден его вызов, возможно зацикливание");
//                }
//                for (String command : commands) {
//                    OutputUtil.printSuccessfulMessage(command);
//                    CommandListener.manager.performCommand(command);
//                }
//                fileHistory.remove(commandArgs[0]);
//            }
//        } catch (WrongAmountOfArgsException | IOException | LoopPossibilityException e) {
//            OutputUtil.printErrorMessage(e.getMessage());
//        }
        return null;
    }
}
