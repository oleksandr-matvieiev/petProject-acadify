package org.example.acadify.exceptions;

import org.example.acadify.Enums.ErrorDefaultMessages;

public class RoleNotFoundExc extends RuntimeException {
    private static final String DEFAULT_MESSAGE = ErrorDefaultMessages.ROLE_NOT_FOUND_EXC.getMessage();

    public RoleNotFoundExc() {
        super(DEFAULT_MESSAGE);
    }

    public RoleNotFoundExc(String message) {
        super(message);
    }
}
