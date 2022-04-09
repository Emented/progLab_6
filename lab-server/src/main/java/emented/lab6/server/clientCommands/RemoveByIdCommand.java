package emented.lab6.server.clientCommands;

import emented.lab6.common.exceptions.IDNotFoundException;
import emented.lab6.common.util.ErrorMessage;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.SuccessMessage;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

public class RemoveByIdCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public RemoveByIdCommand(CollectionManager collectionInWork) {
        super("remove_by_id", 1, "delete a group from a collection by its id", "id");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public Response executeClientCommand(Request request) {
        try {
            collectionInWork.removeBandById(request.getNumericArgument());
            return new Response(new SuccessMessage("Group with ID " + request.getNumericArgument() + " was deleted from collection"));
        } catch (IDNotFoundException e) {
            return new Response(new ErrorMessage(e.getMessage()));
        }
    }
}
