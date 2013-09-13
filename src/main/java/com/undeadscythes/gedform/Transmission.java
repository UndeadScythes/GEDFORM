package com.undeadscythes.gedform;

import java.util.*;

/**
 * A {@link Transmission} is the basis for GEDFORM communication.
 *
 * @author UndeadScythes
 */
public class Transmission extends ArrayList<Record> {
    private static final long serialVersionUID = 1L;

    private int index = 0;

    /**
     *
     */
    public Transmission() {
        this(0);
    }

    /**
     *
     * @param size
     */
    public Transmission(final int size) {
        super(size);
    }

    /**
     *
     * @return An copy of this {@link Transmission}
     */
    public Transmission copy() {
        final Transmission copy = new Transmission(this.size());
        for (Record record : this) {
            copy.add(record.copy());
        }
        return copy;
    }

    /**
     *
     * @return The next {@link Record}
     */
    public Record pullRecord() {
        final Record record = get(index);
        index++;
        return record;
    }

    /**
     *
     * @return True if there is another {@link Record} to pull
     */
    public boolean hasNext() {
        return size() > index;
    }
}
