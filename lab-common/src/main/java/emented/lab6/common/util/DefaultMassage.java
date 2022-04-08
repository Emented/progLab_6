package emented.lab6.common.util;

import emented.lab6.common.abstractions.AbstractResponseMessage;

import java.io.Serializable;

public class DefaultMassage extends AbstractResponseMessage implements Serializable {

    public DefaultMassage(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }

}
