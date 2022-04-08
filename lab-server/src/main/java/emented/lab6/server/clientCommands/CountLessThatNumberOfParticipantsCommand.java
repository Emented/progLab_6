package emented.lab6.server.clientCommands;

import emented.lab6.common.util.SuccessMessage;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

public class CountLessThatNumberOfParticipantsCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public CountLessThatNumberOfParticipantsCommand(CollectionManager CollectionManager) {
        super("count_less_than_number_of_participants",
                1,
                "print the number of groups whose number of participants is less than the specified one",
                "number of participants");
        this.collectionInWork = CollectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response(new SuccessMessage("Groups with fewer participants than " + request.getNumericArgument()
        + ": " + collectionInWork.countLessThanNumberOfParticipants(request.getNumericArgument())));
    }
}
