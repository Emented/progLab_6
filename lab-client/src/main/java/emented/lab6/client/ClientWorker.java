package emented.lab6.client;

import emented.lab6.client.util.AvailableCommands;
import emented.lab6.client.util.ClientSocketWorker;
import emented.lab6.client.util.CommandToSend;
import emented.lab6.client.util.CommandValidators;
import emented.lab6.client.util.RequestCreator;
import emented.lab6.client.util.ScriptReader;
import emented.lab6.client.workWithCommandLine.ClientCommandListener;
import emented.lab6.common.exceptions.WrongAmountOfArgsException;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.TextColoring;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientWorker {
    private final Scanner scanner = new Scanner(System.in);
    private final ClientCommandListener commandListener = new ClientCommandListener(System.in);
    private final RequestCreator requestCreator = new RequestCreator();
    private final int maxPort = 65535;
    private ClientSocketWorker clientSocketWorker;
    private boolean statusOfCommandListening = true;

    public void startClientWorker() {
        ClientConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Welcome to the program! To see the list of commands type HELP"));
        inputAddress();
        inputPort();
        while (statusOfCommandListening) {
            CommandToSend command = commandListener.readCommand();
            if (command != null) {
                if ("exit".equals(command.getCommandName().toLowerCase(Locale.ROOT))) {
                    ClientConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Client shutdown"));
                    toggleStatus();
                } else if (AvailableCommands.SCRIPT_ARGUMENT_COMMAND.equals(command.getCommandName())) {
                    executeScript(command.getCommandArgs());
                } else {
                    if (sendRequest(command)) {
                        receiveResponse();
                    }
                }
            }
        }
    }

    public void toggleStatus() {
        statusOfCommandListening = !statusOfCommandListening;
    }

    private void inputAddress() {
        ClientConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Do you want to use a default server address? [y/n]"));
        try {
            String s = scanner.nextLine().toLowerCase(Locale.ROOT);
            if ("y".equals(s)) {
                clientSocketWorker = new ClientSocketWorker();
            } else if ("n".equals(s)) {
                ClientConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Please enter the server's internet address"));
                String address = scanner.nextLine();
                clientSocketWorker = new ClientSocketWorker();
                clientSocketWorker.setAddress(address);
            } else {
                ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("You entered not valid symbol, try again"));
                inputAddress();
            }
        } catch (UnknownHostException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("Unknown address, try again"));
            inputAddress();
        } catch (SocketException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("Troubles, while opening server port, try again"));
            inputAddress();
        } catch (NoSuchElementException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("An invalid character has been entered, forced shutdown!"));
            System.exit(1);
        }
    }

    private void inputPort() {
        ClientConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Do you want to use a default port? [y/n]"));
        try {
            String s = scanner.nextLine().toLowerCase(Locale.ROOT);
            if ("n".equals(s)) {
                ClientConfig.getTextPrinter().printlnText(TextColoring.getGreenText("Please enter the remote host port (1-65535)"));
                String port = scanner.nextLine();
                try {
                    int portInt = Integer.parseInt(port);
                    if (portInt > 0 && portInt <= maxPort) {
                        clientSocketWorker.setPort(portInt);
                    } else {
                        ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("The number did not fall within the limits, repeat the input"));
                        inputPort();
                    }
                } catch (IllegalArgumentException e) {
                    ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("Error processing the number, repeat the input"));
                    inputPort();
                }
            } else if (!"y".equals(s)) {
                ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("You entered not valid symbol, try again"));
                inputPort();
            }
        } catch (NoSuchElementException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("An invalid character has been entered, forced shutdown!"));
            System.exit(1);
        }
    }

    private boolean sendRequest(CommandToSend command) {
        Request request = requestCreator.createRequestOfCommand(command);
        if (request != null) {
            try {
                clientSocketWorker.sendRequest(request);
                return true;
            } catch (IOException e) {
                ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("An error occurred while serializing the request, try again"));
                return false;
            }
        } else {
            return false;
        }
    }

    private void receiveResponse() {
        try {
            Response response = clientSocketWorker.receiveResponse();
            ClientConfig.getTextPrinter().printlnText(response.toString());
        } catch (SocketTimeoutException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("The waiting time for a response from the server has been exceeded, try again later"));
        } catch (IOException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("An error occurred while receiving a response from the server"));
        } catch (ClassNotFoundException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("The response came damaged"));
        }
    }

    private void executeScript(String[] args) {
        try {
            CommandValidators.validateAmountOfArgs(args, 1);
            ScriptReader reader = new ScriptReader();

            if (ClientConfig.getHistoryOfScripts().contains(args[0])) {
                ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("Possible looping, change your script"));
            } else {
                reader.readCommandsFromFile(args[0]);
                ClientConfig.getHistoryOfScripts().add(args[0]);
                ArrayList<CommandToSend> commands = reader.getCommandsFromFile();
                for (CommandToSend command : commands) {
                    ClientConfig.getTextPrinter().printlnText(TextColoring.getBlueText("Executing... " + command.getCommandName()));
                    if ("execute_script".equals(command.getCommandName())) {
                        executeScript(command.getCommandArgs());
                    } else {
                        if (sendRequest(command)) {
                            receiveResponse();
                            ClientConfig.getHistoryOfScripts().remove(command.getCommandName());
                        }
                    }
                }
            }
        } catch (WrongAmountOfArgsException | IOException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
        } catch (NoSuchElementException e) {
            ClientConfig.getTextPrinter().printlnText(TextColoring.getRedText("An invalid character has been entered, forced shutdown!"));
            System.exit(1);
        }
    }

}
