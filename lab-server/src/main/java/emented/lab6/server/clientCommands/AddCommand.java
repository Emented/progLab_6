package emented.lab6.server.clientCommands;

import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.SuccessMessage;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

public class AddCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public AddCommand(CollectionManager collectionInWork) {
        super("add", 0, "add a new item to the collection");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public Response executeClientCommand(Request request) {
        collectionInWork.addMusicBand(request.getBandArgument());
        return new Response(new SuccessMessage("New element was successfully added!"), request.getBandArgument());
    }
}
