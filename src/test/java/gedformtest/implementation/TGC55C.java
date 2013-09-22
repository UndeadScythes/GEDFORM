package gedformtest.implementation;

import com.undeadscythes.gedform.*;
import com.undeadscythes.gedform.exception.*;

/**
 * @author UndeadScythes
 */
public class ABCs {
    public static final Cluster CLUSTER;

    static {
        CLUSTER = new Cluster(6);
        try {
            CLUSTER.add(new LineStruct("0 A"));
            CLUSTER.add(new LineStruct("1 @B1@ B"));
            CLUSTER.add(new LineStruct("2 C test"));
            CLUSTER.add(new LineStruct("2 D @B1@"));
            CLUSTER.add(new LineStruct("1 @B2@ B test"));
            CLUSTER.add(new LineStruct("2 C @B2@"));
        } catch (ParsingException ex) {}
    }
}
