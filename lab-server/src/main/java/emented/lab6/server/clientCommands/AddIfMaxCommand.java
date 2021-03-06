package emented.lab6.server.clientCommands;

import emented.lab6.common.exceptions.GroupNotMaxException;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

public class AddIfMaxCommand extends AbstractClientCommand {
    private final CollectionManager collectionInWork;

    public AddIfMaxCommand(CollectionManager collectionInWork) {
        super("add_if_max", 0, "add a new item to the collection if its value exceeds the value of the largest item in this collection");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public Response executeClientCommand(Request request) {
        try {
            collectionInWork.addIfMax(request.getBandArgument());
            return new Response(TextColoring.getGreenText("New element was successfully added!"), request.getBandArgument());
        } catch (GroupNotMaxException e) {
            return new Response(TextColoring.getRedText(e.getMessage()));
        }
    }
}
