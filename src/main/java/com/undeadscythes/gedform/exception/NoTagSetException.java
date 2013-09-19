package com.undeadscythes.gedform.exception;

/**
 * Thrown when a value is requested for a tag that has no value set or is itself
 * not set.
 *
 * @author UndeadScythes
 */
public class NoTagSetException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Provide the tag that was not set.
     */
    public NoTagSetException(final String tag) {
        super("No value set for tag " + tag + ".");
    }
}
