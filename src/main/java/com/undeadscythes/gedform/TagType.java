package com.undeadscythes.gedform;

/**
 * A {@link TagType} further divides the {@link Tag}s for ease of processing.
 *
 * @author UndeadScythes
 */
public enum TagType {
    /**
     * A temporal cluster with a date and a place
     */
    EVENT,
    /**
     * A cluster describing a property or characteristic
     */
    FACT,
    /**
     * A cluster providing evidence for a {@link TagType#FACT}
     */
    CITATION,
    /**
     * A custom defined cluster
     */
    CUSTOM,
    /**
     * An internal relationship between records
     */
    XREF;
}
