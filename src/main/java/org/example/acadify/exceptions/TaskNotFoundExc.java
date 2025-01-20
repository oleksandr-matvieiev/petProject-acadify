package org.example.acadify.exceptions;

import org.example.acadify.Enums.ErrorDefaultMessages;

public class TaskNotFoundExc extends RuntimeException {
    private static final String DEFAULT_MESSAGE = ErrorDefaultMessages.TASK_NOT_FOUND_EXC.getMessage();

    public TaskNotFoundExc() {
        super(DEFAULT_MESSAGE);
    }

    public TaskNotFoundExc(String message) {
        super(message);
    }
}
