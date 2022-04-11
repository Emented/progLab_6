package emented.lab6.server.clientCommands;

import emented.lab6.common.entities.MusicBand;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.SizeAnalyzer;
import emented.lab6.common.util.TextColoring;
import emented.lab6.server.abstractions.AbstractClientCommand;
import emented.lab6.server.util.CollectionManager;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class ShowCommand extends AbstractClientCommand {

    private final CollectionManager collectionInWork;

    public ShowCommand(CollectionManager collectionInWork) {
        super("show", 0, "display all the elements of the collection and information about them");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public Response executeClientCommand(Request request) {
        if (collectionInWork.getMusicBands().isEmpty()) {
            return new Response(TextColoring.getGreenText("Collection is empty"));
        } else {
            return new Response(TextColoring.getGreenText("Elements of collection:"), collectionInWork.getMusicBands());
        }
    }
}
