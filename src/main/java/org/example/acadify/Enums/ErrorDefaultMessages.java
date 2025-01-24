package org.example.acadify.Enums;

import lombok.Getter;

@Getter
public enum ErrorDefaultMessages {
    ILLEGAL_ARGUMENT_EXC("User use wrong arguments."),
    ROLE_NOT_FOUND_EXC("Role not found."),
    USER_NOT_FOUND_EXC("User not found."),
    TASK_NOT_FOUND_EXC("Task not found."),
    GROUT_NOT_FOUND_EXC("Group not found."),
    SUBJECT_NOT_FOUND_EXC("Subject not found.");

    private final String message;

    ErrorDefaultMessages(String message) {
        this.message = message;
    }
}
