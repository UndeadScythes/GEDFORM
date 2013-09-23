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

    private int index = 0;

    /**
     * Parse a file into a {@link Transmission}.
     *
     * @deprecated Added new constructor {@link #Transmission(File) Transmission(File)}
     */
    @Deprecated
    public static Transmission parse(final File file) throws ParsingException {
        return new Transmission(file);
    }

    /**
     * Parse a file into a {@link Transmission}.
     */
    public Transmission(final File file) throws ParsingException {
        super(0);
        final BetterReader reader;
        try {
            reader = new BetterReader(file);
        } catch (FileNotFoundException ex) {
            throw new ParsingException("Could not find specified file.", ex);
        }
        parse(reader);
    }

    /**
     * Parse a file with a given encoding into a {@link Transmission}.
     */
    public Transmission(final File file, final String encoding) throws ParsingException, UnsupportedEncodingException {
        super(0);
        final BetterReader reader;
        try {
            reader = new BetterReader(file, encoding);
        } catch (FileNotFoundException ex) {
            throw new ParsingException("Could not find specified file.", ex);
        }
        parse(reader);
    }

    private void parse(final BetterReader reader) throws ParsingException {
        Cluster cluster = new Cluster(0);
        int lineNo = -1;
        while (reader.hasNext()) {
            final String line = reader.getLine();
            final LineStruct struct;
            try {
                struct = new LineStruct(line);
            } catch (ParsingException ex) {
                throw new ParsingException("Could not parse line " + reader.getLineNo() + ".", ex);
            }
            if ("CONT".equals(struct.tag)) {
                cluster.set(lineNo, cluster.get(lineNo).cont(struct.value));
            } else if ("CONC".equals(struct.tag)) {
                cluster.set(lineNo, cluster.get(lineNo).conc(struct.value));
            } else {
                lineNo++;
                if (struct.level == 0 && !cluster.isEmpty()) {
                    add(cluster);
                    cluster = new Cluster(0);
                    lineNo = 0;
                }
                cluster.add(struct);
            }
        }
        add(cluster);
    }

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
