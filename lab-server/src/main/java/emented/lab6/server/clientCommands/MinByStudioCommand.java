package emented.lab6.server.clientCommands;

import emented.lab6.common.exceptions.CollectionIsEmptyException;
import emented.lab6.common.util.ErrorMessage;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.SuccessMessage;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

public class MinByStudioCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public MinByStudioCommand(CollectionManager CollectionManager) {
        super("min_by_studio", 0, "output any object from the collection whose studio field value is minimal");
        this.collectionInWork = CollectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        try {
            return new Response(new SuccessMessage("Minimal element:"), collectionInWork.returnMinByStudio());
        } catch (CollectionIsEmptyException e) {
            return new Response(new ErrorMessage(e.getMessage()));
        }
    }
}
