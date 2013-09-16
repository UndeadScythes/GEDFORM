package com.undeadscythes.gedform.exception;

/**
 * @author UndeadScythes
 */
public class NoValidTagException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public final String tag;
    /**
     *
     * @param tag
     */
    public NoValidTagException(final String tag) {
        super("No valid type found with the tag " + tag + ".");
        this.tag = tag;
    }
}
