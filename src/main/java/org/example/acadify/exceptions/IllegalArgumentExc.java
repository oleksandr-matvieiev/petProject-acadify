package org.example.acadify.exceptions;

import org.example.acadify.Enums.ErrorDefaultMessages;

public class IllegalArgumentExc extends RuntimeException {
    private static final String DEFAULT_MESSAGE = ErrorDefaultMessages.ILLEGAL_ARGUMENT_EXC.getMessage();

    public IllegalArgumentExc() {
        super(DEFAULT_MESSAGE);
    }

    public IllegalArgumentExc(String message) {
        super(message);
    }
}
