package emented.lab6.client.workWithCommandLine;

import emented.lab6.client.ClientConfig;
import emented.lab6.client.util.CommandToSend;
import emented.lab6.common.util.TextColoring;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientCommandListener {

    private final Scanner sc;


    public ClientCommandListener(InputStream inputStream) {
        sc = new Scanner(inputStream);
    }

    public CommandToSend readCommand() {
        try {
            ClientConfig.getTextPrinter().printText(TextColoring.getBlueText("Enter a command: "));
            String[] splitedInput = sc.nextLine().split(" ");
            String commandName = splitedInput[0].toLowerCase(Locale.ROOT);
            String[] commandsArgs = Arrays.copyOfRange(splitedInput, 1, splitedInput.length);
            return new CommandToSend(commandName, commandsArgs);
        } catch (NoSuchElementException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("An invalid character has been entered, forced shutdown!"));
            System.exit(1);
            return null;
        }
    }

}
