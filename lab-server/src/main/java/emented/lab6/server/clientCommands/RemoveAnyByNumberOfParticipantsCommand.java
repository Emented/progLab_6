package emented.lab6.server.clientCommands;

import emented.lab6.common.exceptions.CollectionIsEmptyException;
import emented.lab6.common.exceptions.GroupNotFoundException;
import emented.lab6.common.util.ErrorMessage;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.SuccessMessage;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

public class RemoveAnyByNumberOfParticipantsCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public RemoveAnyByNumberOfParticipantsCommand(CollectionManager collectionManager) {
        super("remove_any_by_number_of_participants",
                1,
                "delete a group with a specified number of members from the collection",
                "number of participants");
        this.collectionInWork = collectionManager;
    }

    @Override
    public Response executeClientCommand(Request request) {
        try {
            collectionInWork.removeAnyByNumberOfParticipants(request.getNumericArgument());
            return new Response(new SuccessMessage("MusicBand with " + request.getNumericArgument() + " participants was removed"));
        } catch (GroupNotFoundException | CollectionIsEmptyException e) {
            return new Response(new ErrorMessage(e.getMessage()));
        }
    }
}
