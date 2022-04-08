package emented.lab6.server.abstractions;

import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;

public abstract class AbstractServerCommand {

    private final String name;
    private final String description;

    public AbstractServerCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract String executeCommand();

    @Override
    public String toString() {
        return "Name of command: " + name + ", description: " + description;
    }
}
