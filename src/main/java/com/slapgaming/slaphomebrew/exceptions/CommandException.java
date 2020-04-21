package com.slapgaming.slaphomebrew.exceptions;

public class CommandException extends Exception {

    public CommandException(String message) {
        super(message);
    }

    /**
     * Create a new CommandException
     * @param msg The error message
     */
    public CommandException(ErrorMsg msg) {
        super(msg.toString());
    }

}
