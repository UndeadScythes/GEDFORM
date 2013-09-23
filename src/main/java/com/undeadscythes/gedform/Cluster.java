package com.undeadscythes.gedform;

import com.undeadscythes.gedform.exception.*;
import java.util.*;

/**
 * A GEDFORM {@link Transmission} contains a number of {@link Cluster}s each
 * containing data about a distinct entity.
 *
 * @author UndeadScythes
 */
public class Cluster extends ArrayList<LineStruct> {
    private static final long serialVersionUID = 1L;

    /**
     * Convenience field for an empty {@link Cluster}.
     */
    public static final Cluster EMPTY = new Cluster(0);

    private int index = 0;

    /**
     * New {@link Cluster} from a given list of {@link LineStruct}s.
     */
    public Cluster(final List<LineStruct> list) {
        super(list.size());
        addAll(list);
    }

    /**
     * Convenience method for creating {@link Cluster Clusters} from
     * {@link String Strings}, assuming a single element {@link LineStruct}
     * list.
     */
    public Cluster(final String line) throws ParsingException {
        this(Arrays.asList(new LineStruct(line)));
    }

    /**
     * {@link Cluster} with a given initial capacity.
     */
    public Cluster(final int size) {
        super(size);
    }

    /**
     * Get the first {@link LineStruct} of this {@link Cluster}.
     */
    public LineStruct pullHead() {
        index = 1;
        return get(0);
    }

    /**
     * Reset the reading iterator to zero.
     */
    public void reset() {
        index = 0;
    }

    /**
     * Get the next sub-{@link Cluster}.
     */
    public Cluster pullCluster() {
        final Cluster record = new Cluster(this.size());
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
     */
    public boolean hasNext() {
        return listIterator(index).hasNext();
    }

    /**
     * Get an immutable copy of this {@link Cluster}.
     */
    public Cluster copy() {
        final Cluster cluster = new Cluster(this.size());
        for (LineStruct line : this) {
            cluster.add(line.copy());
        }
        return cluster;
    }

    /**
     * Get the {@link LineStruct#tag tag} of the head of this {@link Cluster}, all
     * sub-{@link Cluster}s should be related to this tag.
     */
    public String getTag() {
        return get(0).tag;
    }

    /**
     * Get the value of the first available line with a matching tag.
     */
    public String getValue(final String tag) throws NoTagSetException {
        final int subLevel = get(0).level - 1;
        for (LineStruct line : this) {
            if (line.level == subLevel && line.tag.equals(tag)) return line.value;
        }
        throw new NoTagSetException(tag);
    }
}
