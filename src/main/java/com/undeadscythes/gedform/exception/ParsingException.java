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
     *
     * @param message Description of this parse error
     * @param exception Cause of this parse error
     */
    public ParsingException (final String message, final Exception exception) {
        super(message, exception);
    }

    /**
     * When no previous exception was caught.
     *
     * @param message Cause of this parse error
     */
    public ParsingException (final String message) {
        super(message);
    }
}
