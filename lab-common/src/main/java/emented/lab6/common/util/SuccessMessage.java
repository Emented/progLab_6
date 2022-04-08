package emented.lab6.common.util;

import emented.lab6.common.abstractions.AbstractResponseMessage;

import java.io.Serializable;

public class SuccessMessage extends AbstractResponseMessage implements Serializable {

    public SuccessMessage(String message) {
        super(TextColoring.getGreenText(message));
    }

    public String getMessage() {
        return TextColoring.getGreenText(super.getMessage());
    }
}
