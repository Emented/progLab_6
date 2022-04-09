package emented.lab6.server.serverCommands;

import emented.lab6.common.util.TextColoring;
import emented.lab6.server.ServerConfig;
import emented.lab6.server.abstractions.AbstractServerCommand;

import java.io.IOException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerExitCommand extends AbstractServerCommand {

    private final Scanner scanner;

    public ServerExitCommand(Scanner scanner) {
        super("exit", "shut down the server (you'll be asked to store all the changes)");
        this.scanner = scanner;
    }

    @Override
    public String executeServerCommand() {
        chooseSaving();
        ServerConfig.toggleRun();
        return TextColoring.getGreenText("Server shutdown");
    }

    private void chooseSaving() {
        ServerConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Do you want to save changes? [y/n]"));
        try {
            String s = scanner.nextLine().toLowerCase(Locale.ROOT);
            if ("n".equals(s)) {
                ServerConfig.getTextPrinter().printlnText(TextColoring.getGreenText("You lost all of your data )="));
            } else if ("y".equals(s)) {
                ServerConfig.getParser().writeToXMLofExistingInstance(ServerConfig.getCollectionOfMusicBands());
                ServerConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Collection was successfully saved"));
            } else {
                ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText("You entered not valid symbol, try again"));
                chooseSaving();
            }
        } catch (NoSuchElementException e) {
            ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText("An invalid character has been entered, forced shutdown!"));
            System.exit(1);
        } catch (IOException e) {
            ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
        }
    }
}
