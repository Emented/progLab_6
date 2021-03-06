package emented.lab6.server.clientCommands;

import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

public class CountLessThatNumberOfParticipantsCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public CountLessThatNumberOfParticipantsCommand(CollectionManager collectionManager) {
        super("count_less_than_number_of_participants",
                1,
                "print the number of groups whose number of participants is less than the specified one",
                "number of participants");
        this.collectionInWork = collectionManager;
    }

    @Override
    public Response executeClientCommand(Request request) {
        return new Response(TextColoring.getGreenText("Groups with fewer participants than " + request.getNumericArgument()
                + ": " + collectionInWork.countLessThanNumberOfParticipants(request.getNumericArgument())));
    }
}
