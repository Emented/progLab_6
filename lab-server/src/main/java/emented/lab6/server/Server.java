package emented.lab6.server;

import java.io.IOException;

public final class Server {

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) throws IOException {
        ServerWorker serverApp = new ServerWorker("MusicBands.xml");
        serverApp.startServerWorker();
    }
}
