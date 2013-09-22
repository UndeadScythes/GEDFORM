package gedformtest;

import com.undeadscythes.gedform.*;
import com.undeadscythes.gedform.exception.*;
import gedformtest.implementation.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class ClusterTest extends Assert {
    private Cluster record;

    @Before
    public void init() throws ParsingException {
        record = new TGC55C().pullCluster();
    }

    @Test
    public void testHeader() {
        assertEquals("pullHead-tag", "HEAD", record.pullHead().tag);
        assertEquals("pullHead-level", 0, record.pullHead().level);
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
        assertEquals("getTag", "HEAD", record.getTag());
    }

    @Test
    public void testHasNext() {
        record.pullHead();
        record.pullCluster();
        assertTrue("hasNext-true", record.hasNext());
        for (int i = 0; i < 13; i++) {
            record.pullCluster();
        }
        assertFalse("hasNext-false", record.hasNext());
    }

    @Test
    public void testCluster() {
        assertEquals("pullCluster-1", 35, record.pullCluster().size());
        record.reset();
        record.pullHead();
        assertEquals("pullCluster-2", 17, record.pullCluster().size());
        assertEquals("pullCluster-3", 1, record.pullCluster().size());
    }
}
