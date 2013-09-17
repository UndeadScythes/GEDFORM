package com.undeadscythes.gedform;

import com.undeadscythes.betterreader.*;
import com.undeadscythes.gedform.exception.*;
import java.io.*;
import java.util.*;

/**
 * A {@link Transmission} is the basis for GEDFORM communication.
 *
 * @author UndeadScythes
 */
public class Transmission extends ArrayList<Cluster> {
    private static final long serialVersionUID = 1L;

    /**
     * Parse a file into a {@link Transmission}.
     *
     * @param file
     * @return Parsed {@link Transmission}
     * @throws ParsingException When a parsing error occurs
     */
    public static Transmission parse(final File file) throws ParsingException {
        final BetterReader reader;
        try {
            reader = new BetterReader(file);
        } catch (FileNotFoundException ex) {
            throw new ParsingException("Could not find specified file.", ex);
        }
        final Transmission transmission = new Transmission();
        Cluster cluster = new Cluster();
        while (reader.hasNext()) {
            final String line = reader.getLine();
            final LineStruct struct;
            try {
                struct = new LineStruct(line);
            } catch (ParsingException ex) {
                throw new ParsingException("Could not parse line #" + reader.getLineNo() + ".", ex);
            }
            if (struct.level == 0 && !cluster.isEmpty()) {
                transmission.add(cluster);
                cluster = new Cluster();
            }
            cluster.add(struct);
        }
        transmission.add(cluster);
        return transmission;
    }

    private int index = 0;

    /**
     * Blank {@link Transmission}.
     */
    public Transmission() {
        this(0);
    }

    /**
     * {@link Transmission} with a given initial capacity.
     *
     * @param size Initial capacity
     */
    public Transmission(final int size) {
        super(size);
    }

    /**
     * Copy this {@link Transmission}.
     *
     * @return Mutable copy of this {@link Transmission}
     */
    public Transmission copy() {
        final Transmission copy = new Transmission(this.size());
        for (Cluster cluster : this) {
            copy.add(cluster.copy());
        }
        return copy;
    }

    /**
     * Grabs the next {@link Cluster} in this {@link Transmission}.
     *
     * @return The next {@link Cluster}
     */
    public Cluster pullCluster() {
        final Cluster cluster = get(index);
        index++;
        return cluster;
    }

    /**
     * Check if there is another {@link Cluster} to return in this
     * {@link Transmission}.
     *
     * @return True if there is another {@link Cluster} to pull
     */
    public boolean hasNext() {
        return size() > index;
    }

    /**
     * YAMLize this {@link Transmission}.
     *
     * @return This {@link Transmission} in valid YAML form
     */
    public String yamlize() {
        return ""; //TODO: Implement the yamlizer
    }

    /**
     * Validate this {@link Transmission}.
     *
     * @return True if this {@link Transmission} has valid form
     */
    public boolean verify() {
        return true; //TODO: Implement verifier
    }
}
