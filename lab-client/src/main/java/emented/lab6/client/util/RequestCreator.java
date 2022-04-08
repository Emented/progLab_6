package emented.lab6.client.util;

import emented.lab6.client.ClientConfig;
import emented.lab6.client.workWithCommandLine.MusicBandGenerator;
import emented.lab6.common.exceptions.WrongAmountOfArgsException;
import emented.lab6.common.exceptions.WrongArgException;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.TextColoring;

public class RequestCreator {

    public Request createRequestOfCommand(CommandToSend command) {
        String name = command.getCommandName();
        if (AvailableCommands.commandsWithoutArgs.contains(name)) {
            return createRequestWithoutArgs(command);
        } else if (AvailableCommands.commandsWithIdArg.contains(name)) {
            return createRequestWithID(command);
        } else if (AvailableCommands.commandsWithNumberOfParticipantsArg.contains(name)) {
            return createRequestWithNumOfParticipants(command);
        } else if (AvailableCommands.commandsWithBandArg.contains(name)) {
            return createRequestWithBand(command);
        } else if (AvailableCommands.commandsWithBandIdArgs.contains(name)) {
            return createRequestWithBandID(command);
        } else {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("There is no such command, type HELP to get list on commands"));
            return null;
        }
    }

    private Request createRequestWithoutArgs(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 0);
            return new Request(command.getCommandName());
        } catch (WrongAmountOfArgsException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
            return null;
        }
    }

    private Request createRequestWithID(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 1);
            long id = CommandValidators.validateArg(arg -> ((long) arg) > 0,
                    "ID must be greater then 0",
                    Long::parseLong,
                    command.getCommandArgs()[0]);
            return new Request(command.getCommandName(), id);
        } catch (WrongAmountOfArgsException | WrongArgException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
            return null;
        } catch (IllegalArgumentException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("Wrong data type of argument"));
            return null;
        }
    }

    private Request createRequestWithNumOfParticipants(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 1);
            long numberOfParticipants = CommandValidators.validateArg(arg -> ((long) arg) > 0,
                    "Number of participants must be greater then 0",
                    Long::parseLong,
                    command.getCommandArgs()[0]);
            return new Request(command.getCommandName(), numberOfParticipants);
        } catch (WrongAmountOfArgsException | WrongArgException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
            return null;
        } catch (IllegalArgumentException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("Wrong data type of argument"));
            return null;
        }
    }

    private Request createRequestWithBand(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 0);
            MusicBandGenerator generator = new MusicBandGenerator();
            generator.setVariables();
            return new Request(command.getCommandName(), generator.getGeneratedMusicBand());
        } catch (WrongAmountOfArgsException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
            return null;
        }
    }

    private Request createRequestWithBandID(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 1);
            long id = CommandValidators.validateArg(arg -> ((long) arg) > 0,
                    "ID must be greater then 0",
                    Long::parseLong,
                    command.getCommandArgs()[0]);
            MusicBandGenerator generator = new MusicBandGenerator();
            generator.setVariables();
            return new Request(command.getCommandName(), id, generator.getGeneratedMusicBand());
        } catch (WrongAmountOfArgsException | WrongArgException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
            return null;
        } catch (IllegalArgumentException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("Wrong data type of argument"));
            return null;
        }
    }
}
