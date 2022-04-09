package emented.lab6.server.threads;

import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.ServerConfig;
import emented.lab6.server.util.ServerSocketWorker;

import java.io.IOException;

public class RequestThread extends Thread {

    private final ServerSocketWorker serverSocketWorker;

    public RequestThread(ServerSocketWorker serverSocketWorker) {
        this.serverSocketWorker = serverSocketWorker;
    }

    @Override
    public void run() {
        while (ServerConfig.getRunning()) {
            try {
                Request acceptedRequest = serverSocketWorker.listenForRequest();
                if (acceptedRequest != null) {
                    Response responseToSend = ServerConfig.getCommandManager().executeClientCommand(acceptedRequest);
                    serverSocketWorker.sendResponse(responseToSend);
                }
            } catch (ClassNotFoundException e) {
                ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText("An error occurred while deserializing the request, try again"));
            } catch (IOException e) {
                ServerConfig.getTextPrinter().printlnText(TextColoring.getRedText(e.getMessage()));
            }
        }
    }
}
