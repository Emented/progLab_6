package emented.lab6.server.serverCommands;

import emented.lab6.server.abstractions.AbstractServerCommand;
import emented.lab6.server.parser.XMLParser;
import emented.lab6.server.util.CollectionManager;

import java.io.IOException;

public class ServerSaveCommand extends AbstractServerCommand {

    private final CollectionManager collectionInWork;
    private final XMLParser parser;

    public ServerSaveCommand(CollectionManager collectionInWork, XMLParser parser) {
        super("save", "save the collection to a file");
        this.collectionInWork = collectionInWork;
        this.parser = parser;
    }

    @Override
    public String executeCommand() {
        try {
            parser.writeToXMLofExistingInstance(collectionInWork);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //TODO
        }
        return null;
    }
}
