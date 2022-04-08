package emented.lab6.server.clientCommands;

import emented.lab6.common.util.*;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

public class ShowCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public ShowCommand(CollectionManager collectionInWork) {
        super("show", 0, "display all the elements of the collection and information about them");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response(new DefaultMassage(TextColoring.getGreenText("Elements of collection:\n") + collectionInWork.show()));
    }
}
