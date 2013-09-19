package com.undeadscythes.gedform.exception;

/**
 * Thrown when an error is encountered while parsing a transmission.
 *
 * @author UndeadScythes
 */
public class ParsingException extends Exception {
    private static final long serialVersionUID = 2L;

    /**
     * Allows chaining of exceptions.
     */
    public ParsingException (final String message, final Exception exception) {
        super(message, exception);
    }

    /**
     * When no previous exception was caught.
     */
    public ParsingException (final String message) {
        super(message);
    }
}
