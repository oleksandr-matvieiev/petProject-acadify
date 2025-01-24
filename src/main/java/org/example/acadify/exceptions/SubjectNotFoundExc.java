package org.example.acadify.exceptions;

import org.example.acadify.Enums.ErrorDefaultMessages;

public class SubjectNotFoundExc extends RuntimeException {
    private static final String DEFAULT_MESSAGE = ErrorDefaultMessages.SUBJECT_NOT_FOUND_EXC.getMessage();

    public SubjectNotFoundExc() {
        super(DEFAULT_MESSAGE);
    }

    public SubjectNotFoundExc(String message) {
        super(message);
    }
}
