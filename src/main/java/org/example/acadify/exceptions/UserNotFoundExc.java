package org.example.acadify.exceptions;

import org.example.acadify.Enums.ErrorDefaultMessages;

public class UserNotFoundExc extends RuntimeException{
    private static final String DEFAULT_MESSAGE= ErrorDefaultMessages.USER_NOT_FOUND_EXC.getMessage();

    public UserNotFoundExc() {
    super(DEFAULT_MESSAGE);
    }

    public UserNotFoundExc(String message) {
        super(message);
    }
}
