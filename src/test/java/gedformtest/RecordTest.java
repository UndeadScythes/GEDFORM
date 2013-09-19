package gedformtest; //TODO: Implement more tests

import com.undeadscythes.gedform.*;
import gedformtest.implementation.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class RecordTest extends Assert {
    private Cluster record;

    @Before
    public void init() {
        record = ABCs.CLUSTER.copy();
    }

    @Test
    public void testHeader() {
        assertEquals("pullHead-tag", "A", record.pullHead().tag);
        assertEquals("popHead-level", 0, record.popHead().level);
        assertEquals("pullHead-level", "B", record.pullHead().tag);
    }

    @Test
    public void testCopy() {
        final Cluster copy = record.copy();
        for (int i = 0; i < record.size(); i++) {
            assertEquals("copy", record.get(i).tag, copy.get(i).tag);
        }
    }

    @Test
    public void testGet() {
        assertEquals("getTag", "A", record.getTag());
    }

    @Test
    public void testHasNext() {
        record.popHead();
        record.pullCluster();
        assertTrue("hasNext-true", record.hasNext());
        record.pullCluster();
        assertFalse("hasNext-false", record.hasNext());
    }

    @Test
    public void testCluster() {
        assertEquals("pullCluster-1", 6, record.pullCluster().size());
        record.reset();
        record.pullHead();
        assertEquals("pullCluster-2", 3, record.pullCluster().size());
        assertEquals("pullCluster-3", 2, record.pullCluster().size());
    }
}
