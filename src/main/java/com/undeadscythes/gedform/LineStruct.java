package com.undeadscythes.gedform;

import com.undeadscythes.gedform.exception.*;
import static java.lang.Integer.*;
import java.util.*;
import org.apache.commons.lang3.*;

/**
 * A {@link LineStruct} represents a single line of a GEDFORM record.
 *
 * @author UndeadScythes
 */
public class LineStruct {
    private static final String XREF_REGEX = "@\\w[^@\\x00-\\x1F\\x7F]{0,19}@";
    private static final int MIN_ELEMENTS = 2;
    private static final int MIN_XREF_ELEMENTS = 3;

    private static String parseValue(final String[] strings, final int start) {
        return StringUtils.join(Arrays.copyOfRange(strings, start, strings.length), " ").replace("@@", "@"); //TODO: Handle escape chars
    }

    private static String parseXREF(final String string) {
        return string.replaceAll("@", "");
    }

    /**
     * Hierarchical level of this line.
     */
    public final int level;

    /**
     * A relational link within the GEDFORM.
     */
    public final String xref;

    /**
     * The {@link LineStruct#tag} of this line.
     */
    public final String tag;

    /**
     * This line's value.
     */
    public final String value;

    /**
     * True if this {@link LineStruct#value} is a pointer.
     */
    public final boolean pointer;

    /**
     * Takes a single line of transmission and divides it into its constituent
     * parts.
     *
     * @param string Line of transmission
     * @throws ParsingException When a parsing error occurs
     */
    public LineStruct(final String string) throws ParsingException {
        final String[] split = string.split(" "); //TODO: Check that pointers are always parsed
        try {
            level = parseInt(split[0]);
        } catch (NumberFormatException ex) {
            throw new ParsingException("Could not parse level.", ex);
        }
        if (split.length < MIN_ELEMENTS) {
            throw new ParsingException("Not enough elements.");
        } else if (split.length == MIN_ELEMENTS) { // LVL TAG
            tag = split[1];
            xref = "";
            value = "";
            pointer = false;
        } else if (split[1].matches(XREF_REGEX)) {
            xref = parseXREF(split[1]);
            tag = split[2];
            if (split.length == MIN_XREF_ELEMENTS) { // LVL XREF TAG
                value = "";
            } else { // LVL XREF TAG VAL
                value = parseValue(split, 3);
            }
            pointer = false;
        } else {
            xref = "";
            tag = split[1];
            final String temp = parseValue(split, 2);
            if (temp.matches(XREF_REGEX)) { // LVL TAG PTR
                value = parseXREF(temp);
                pointer = true;
            } else { // LVL TAG VAL
                value = temp;
                pointer = false;
            }
        }
    }

    private LineStruct(final LineStruct line) {
        this.level = line.level;
        this.tag = line.tag;
        this.xref = line.xref;
        this.value = line.value;
        this.pointer = line.pointer;
    }

    private LineStruct(final int level, final String tag, final String xref, final String value, final boolean pointer) {
        this.level = level;
        this.tag = tag;
        this.xref = xref;
        this.value = value;
        this.pointer = pointer;
    }

    /**
     * Get an immutable copy of this {@link LineStruct}.
     */
    public LineStruct copy() {
        return new LineStruct(this);
    }

    /**
     * Add another line of text onto this {@link LineStruct}.
     */
    public LineStruct cont(final String value) {
        return new LineStruct(level, tag, xref, this.value + "\n" + value, pointer);
    }

    /**
     * Concatenate a line of text onto the end of this {@link LineStruct}.
     */
    public LineStruct conc(final String value) {
        return new LineStruct(level, tag, xref, this.value + value, pointer);
    }
}
