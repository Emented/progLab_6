package emented.lab6.common.abstractions;

import java.io.Serializable;

public abstract class AbstractResponseMessage implements Serializable {

    private final String message;

    public AbstractResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
