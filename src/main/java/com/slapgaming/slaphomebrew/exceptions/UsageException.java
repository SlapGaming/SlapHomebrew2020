package com.slapgaming.slaphomebrew.exceptions;

public class UsageException extends CommandException {

    /**
     * Create a new UsageException
     *
     * Output: Usage: /[usage]
     * @param usage The correct usage
     */
    public UsageException(String usage) {
        super("Usage: /" + usage);

    }

}
