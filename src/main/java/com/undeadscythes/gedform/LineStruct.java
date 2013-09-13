package com.undeadscythes.gedform;

/**
 * A {@link LineStruct} represents a single line of a GEDFORM record.
 *
 * @author UndeadScythes
 */
public class LineStruct {
    /**
     * Hierarchical level of this line
     */
    public final int level;
    /**
     * A relational link within the GEDFORM
     */
    public final String xref;
    /**
     * The {@link Tag} of this line
     */
    public final Tag tag;
    /**
     * This line's value
     */
    public final String value;

    /**
     *
     * @param level
     * @param tag
     * @param value
     */
    public LineStruct(final int level, final Tag tag, final String value) {
        this(level, "", tag, value);
    }

    /**
     *
     * @param level
     * @param tag
     */
    public LineStruct(final int level, final Tag tag) {
        this(level, "", tag, "");
    }

    /**
     *
     * @param level
     * @param xref 
     * @param tag
     */
    public LineStruct(final int level, final String xref, final Tag tag) {
        this(level, xref, tag, "");
    }

    /**
     *
     * @param level
     * @param xref
     * @param tag
     * @param value
     */
    public LineStruct(final int level, final String xref, final Tag tag, final String value) {
        this.level = level;
        this.xref = xref;
        this.tag = tag;
        this.value = value;
    }
}
