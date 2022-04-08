package emented.lab6.client.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AvailableCommands {

    public static final Set<String> commandsWithoutArgs = new HashSet<>();
    public static final Set<String> commandsWithIdArg = new HashSet<>();
    public static final Set<String> commandsWithNumberOfParticipantsArg = new HashSet<>();
    public static final Set<String> commandsWithBandArg = new HashSet<>();
    public static final Set<String> commandsWithBandIdArgs = new HashSet<>();
    public static final String scriptArgumentCommand;


    static {
        Collections.addAll(commandsWithoutArgs,
                "help",
                "show",
                "info",
                "history",
                "min_by_studio",
                "clear"
        );
        Collections.addAll(commandsWithIdArg,
                "remove_by_id"
        );
        Collections.addAll(commandsWithNumberOfParticipantsArg,
                "count_less_than_number_of_participants",
                "remove_any_by_number_of_participants"
        );
        Collections.addAll(commandsWithBandArg,
                "add",
                "add_if_max",
                "remove_greater"
        );
        Collections.addAll(commandsWithBandIdArgs,
                "update");
        scriptArgumentCommand = "execute_script";
    }

}
