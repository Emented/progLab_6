package emented.lab6.server.clientCommands;

import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

public class ClearCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public ClearCommand(CollectionManager collectionInWork) {
        super("clear", 0, "clear the collection");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public Response executeClientCommand(Request request) {
        if (collectionInWork.getMusicBands().isEmpty()) {
            return new Response(TextColoring.getGreenText("Collection is already empty"));
        } else {
            collectionInWork.clearCollection();
            return new Response(TextColoring.getGreenText("The collection has been cleared"));
        }
    }
}
