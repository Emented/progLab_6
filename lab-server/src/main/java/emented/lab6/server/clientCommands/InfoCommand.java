package emented.lab6.server.clientCommands;

import emented.lab6.common.util.*;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;
import org.w3c.dom.Text;

public class InfoCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public InfoCommand(CollectionManager collectionInWork) {
        super("info", 0, "display information about the collection");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response(new DefaultMassage(TextColoring.getGreenText("Info about collection:\n") + collectionInWork.returnInfo()));
    }
}
