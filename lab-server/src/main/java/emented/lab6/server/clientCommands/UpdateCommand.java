package emented.lab6.server.clientCommands;

import emented.lab6.common.exceptions.IDNotFoundException;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

public class UpdateCommand extends AbstractClientCommand {


    private final CollectionManager collectionInWork;

    public UpdateCommand(CollectionManager collectionManager) {
        super("update", 1,
                "update the value of a collection item whose id is equal to the specified one",
                "id");
        this.collectionInWork = collectionManager;
    }

    @Override
    public Response executeClientCommand(Request request) {
        try {
            collectionInWork.updateById(request.getNumericArgument(), request.getBandArgument());
            return new Response(TextColoring.getGreenText("Element with ID " + request.getNumericArgument() + " was updated"));
        } catch (IDNotFoundException e) {
            return new Response(TextColoring.getRedText(e.getMessage()));
        }
    }
}
