package emented.lab6.server.clientCommands;

import emented.lab6.common.entities.MusicBand;
import emented.lab6.common.exceptions.CollectionIsEmptyException;
import emented.lab6.common.util.ErrorMessage;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.SuccessMessage;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

import java.util.Set;

public class RemoveGreaterCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public RemoveGreaterCommand(CollectionManager CollectionManager) {
        super("remove_greater", 0, "remove all items from the collection that exceed the specified");
        this.collectionInWork = CollectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        try {
            Set<MusicBand> res = collectionInWork.removeIfGreater(request.getBandArgument());
            if (res.isEmpty()) {
                return new Response(new SuccessMessage("Not a single item has been deleted"));
            } else {
                return new Response(new SuccessMessage("These items have been removed from the collection:"), res);
            }
        } catch (CollectionIsEmptyException e) {
            return new Response(new ErrorMessage(e.getMessage()));
        }
    }
}
