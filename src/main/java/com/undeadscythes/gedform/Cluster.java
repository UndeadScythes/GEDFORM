package com.undeadscythes.gedform;

import java.util.*;

/**
 * A GEDFORM {@link Transmission} contains a number of {@link Cluster}s each
 * containing data about a distinct entity.
 *
 * @author UndeadScythes
 */
public class Cluster extends ArrayList<LineStruct> {
    private static final long serialVersionUID = 1L;

    private int index = 0;

    /**
     * Blank {@link Cluster}.
     */
    public Cluster() {
        this(0);
    }

    /**
     * New {@link Cluster} from a given list of {@link LineStruct}s.
     *
     * @param list {@link LineStruct}s the {@link Cluster} should contain
     */
    public Cluster(final List<LineStruct> list) {
        super(list.size());
        addAll(list);
    }

    /**
     * {@link Cluster} with a given initial capacity.
     *
     * @param size
     */
    public Cluster(final int size) {
        super(size);
    }

    /**
     * Grab the first {@link LineStruct} of this {@link Cluster}.
     *
     * @return Header {@link LineStruct}
     */
    public LineStruct pullHead() {
        index = 1;
        return get(0);
    }

    /**
     * Grab and remove the first {@link LineStruct} of this {@link Cluster},
     * this method deletes the head from the {@link Cluster}.
     *
     * @return Header {@link LineStruct}
     */
    public LineStruct popHead() {
        final LineStruct line = get(0);
        remove(0);
        return line;
    }

    /**
     * Reset the reading iterator to zero.
     */
    public void reset() {
        index = 0;
    }

    /**
     * Grab the next sub-{@link Cluster}.
     *
     * @return Next {@link Cluster}
     */
    public Cluster pullCluster() {
        final Cluster record = new Cluster();
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
     * Check if there are any more sub{@link Cluster}s to read.
     *
     * @return Check if there is another cluster to read
     */
    public boolean hasNext() {
        return listIterator(index).hasNext();
    }

    /**
     * Copy this {@link Cluster}.
     *
     * @return Mutable copy of this {@link Cluster}
     */
    public Cluster copy() {
        final Cluster record = new Cluster(this.size());
        record.addAll(this);
        return record;
    }

    /**
     * Grab the {@link LineStruct#tag} of the head of this {@link Cluster}, all
     * sub-{@link Cluster}s should be related to this tag.
     *
     * @return {@link LineStruct#tag} of the record header
     */
    public String getTag() {
        return get(0).tag;
    }
}
