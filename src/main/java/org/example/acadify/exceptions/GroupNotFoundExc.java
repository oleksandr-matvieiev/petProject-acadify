package org.example.acadify.exceptions;

import org.example.acadify.Enums.ErrorDefaultMessages;

public class GroupNotFoundExc extends RuntimeException {
    private static final String DEFAULT_MESSAGE = ErrorDefaultMessages.GROUT_NOT_FOUND_EXC.getMessage();

    public GroupNotFoundExc() {
        super(DEFAULT_MESSAGE);
    }

    public GroupNotFoundExc(String message) {
        super(message);
    }
}
