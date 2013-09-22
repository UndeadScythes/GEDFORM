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
     */
    public static Transmission parse(final File file) throws ParsingException {
        final BetterReader reader;
        try {
            reader = new BetterReader(file);
        } catch (FileNotFoundException ex) {
            throw new ParsingException("Could not find specified file.", ex);
        }
        final Transmission transmission = new Transmission(0);
        Cluster cluster = new Cluster(0);
        while (reader.hasNext()) {
            final String line = reader.getLine();
            final LineStruct struct;
            try {
                struct = new LineStruct(line); //TODO: Add support for CONT and CONC
            } catch (ParsingException ex) {
                throw new ParsingException("Could not parse line " + reader.getLineNo() + ".", ex);
            }
            if (struct.level == 0 && !cluster.isEmpty()) {
                transmission.add(cluster);
                cluster = new Cluster(0);
            }
            cluster.add(struct);
        }
        transmission.add(cluster);
        return transmission;
    }

    private int index = 0;

    /**
     * {@link Transmission} with a given initial capacity.
     */
    public Transmission(final int size) {
        super(size);
    }

    /**
     * {@link Transmission} with zero initial capacity.
     *
     * @see #Transmission(int) Transmission(int)
     */
    public Transmission() {
        super(0);
    }

    /**
     * Get an immutable copy this {@link Transmission}.
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
     */
    public Cluster pullCluster() {
        final Cluster cluster = get(index);
        index++;
        return cluster;
    }

    /**
     * Check if there is another {@link Cluster} to return in this
     * {@link Transmission}.
     */
    public boolean hasNext() {
        return size() > index;
    }

    /**
     * YAMLize this {@link Transmission}.
     */
    public String yamlize() {
        return ""; //TODO: Implement the yamlizer
    }

    /**
     * Validate this {@link Transmission}.
     */
    public boolean verify() {
        return true; //TODO: Implement verifier
    }

    /**
     * XMLize this {@link Transmission}.
     */
    public String xmlize() {
        return ""; //TODO: Implement me
    }
}
