package com.undeadscythes.gedform;

import java.util.*;

/**
 * A GEDFORM {@link Transmission} contains a number of {@link Record}s each
 * containing data about a distinct entity.
 *
 * @author UndeadScythes
 */
public class Record extends ArrayList<LineStruct> {
    private static final long serialVersionUID = 1L;

    private int index = 0;

    /**
     *
     */
    public Record() {
        this(0);
    }

    /**
     *
     * @param size
     */
    public Record(final int size) {
        super(size);
    }

    /**
     *
     * @return Header {@link LineStruct}
     */
    public LineStruct pullHead() {
        index = 1;
        return get(0);
    }

    /**
     *
     * @return Header {@link LineStruct}
     */
    public LineStruct popHead() {
        final LineStruct line = get(0);
        remove(0);
        return line;
    }

    /**
     *
     */
    public void reset() {
        index = 0;
    }

    /**
     *
     * @return Next {@link Record}
     */
    public Record pullCluster() {
        final Record record = new Record();
        if (hasNext()) {
            final ListIterator<LineStruct> iter = listIterator(index);
            final int level = get(index).level;
            LineStruct line = iter.next();
            do {
                record.add(line);
                index++;
                if (!iter.hasNext()) break;
                line = iter.next();
            } while (line.level > level);
        }
        return record;
    }

    /**
     *
     * @return Check if there is another cluster to read
     */
    public boolean hasNext() {
        return listIterator(index).hasNext();
    }

    /**
     *
     * @return A copy of this {@link Record}
     */
    public Record copy() {
        final Record record = new Record(this.size());
        record.addAll(this);
        return record;
    }

    /**
     *
     * @return The {@link Tag} of the record header
     */
    public Tag getTag() {
        return get(0).tag;
    }

    /**
     *
     * @return The {@link TagType} of the record header
     */
    public TagType getType() {
        return getTag().getType();
    }
}
