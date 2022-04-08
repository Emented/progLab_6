package emented.lab6.common.util;

import emented.lab6.common.abstractions.AbstractResponseMessage;

import java.io.Serializable;

public class ErrorMessage extends AbstractResponseMessage implements Serializable {

    public ErrorMessage(String message) {
        super(TextColoring.getRedText(message));
    }

    @Override
    public String getMessage() {
        return TextColoring.getRedText(super.getMessage());
    }
}
