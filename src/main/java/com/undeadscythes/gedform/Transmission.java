package com.undeadscythes.gedform;

import com.undeadscythes.gedform.exception.*;
import java.io.*;
import static java.lang.Integer.*;
import static java.util.Arrays.*;
import java.util.*;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * A {@link Transmission} is the basis for GEDFORM communication.
 *
 * @author UndeadScythes
 */
public class Transmission extends ArrayList<Record> {
    private static final long serialVersionUID = 1L;
    private static final String XREF_REGEX = "@\\w[^@\\x00-\\x1F\\x7F]{0,19}@";

    /**
     *
     * @param file
     * @param tags
     * @return Pair containing parsed transmission and a set of unused tags
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Map.Entry<Transmission, Set<String>> parse(final File file, final Tag[] tags) throws FileNotFoundException, IOException {
        final BufferedReader input = new BufferedReader(new FileReader(file));
        final Transmission transmission = new Transmission();
        Record record = new Record();
        final Set<String> unused = new HashSet<String>(0);
        String line;
        while ((line = input.readLine()) != null) {
            final String[] split = line.trim().split(" ");
            final int level = parseInt(split[0]); //TODO: Catch integer parse exceptions
            if (level == 0 && !record.isEmpty()) {
                transmission.add(record);
                record = new Record();
            }
            try {
                if (split.length < 2) {
                    //TODO: Throw parse error
                } else if (split.length == 2) {
                    record.add(new LineStruct(level, getTag(tags, split[1]))); // LVL TAG
                } else if (split[1].matches(XREF_REGEX)) {
                    final String xref = split[1].replaceAll("@", "");
                    final Tag tag = getTag(tags, split[2]);
                    if (split.length == 3) {
                        record.add(new LineStruct(level, xref, tag)); // LVL XREF TAG
                    } else {
                        final String value = join(copyOfRange(split, 3, split.length), " ").replace("@@", "@"); //TODO: Handle escape chars
                        record.add(new LineStruct(level, xref, tag, value)); // LVL XREF TAG VAL
                    }
                } else {
                    final Tag tag = getTag(tags, split[1]);
                    final String value = join(copyOfRange(split, 3, split.length), " ").replace("@@", "@"); //TODO: Handle escape chars
                    if (value.matches(XREF_REGEX)) {

                        record.add(new LineStruct(level, tag, value.replaceAll("@", ""), true)); // LVL TAG PTR
                    } else {
                        record.add(new LineStruct(level, tag, value)); // LVL TAG VAL
                    }
                }
            } catch (NoValidTagException ex) {
                unused.add(ex.tag);
            }
        }
        return new AbstractMap.SimpleEntry<Transmission, Set<String>>(transmission, unused);
    }

    private static Tag getTag(final Tag[] tags, final String tag) {
        for (final Tag test : tags) {
            if (test.getTag().equals(tag)) return test;
        }
        throw new NoValidTagException(tag);
    }

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
