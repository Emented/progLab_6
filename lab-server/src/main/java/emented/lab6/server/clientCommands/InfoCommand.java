package emented.lab6.server.clientCommands;

import emented.lab6.common.util.DefaultMassage;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

public class InfoCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public InfoCommand(CollectionManager collectionInWork) {
        super("info", 0, "display information about the collection");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public Response executeClientCommand(Request request) {
        return new Response(new DefaultMassage(TextColoring.getGreenText("Info about collection:\n") + collectionInWork.returnInfo()));
    }
}
