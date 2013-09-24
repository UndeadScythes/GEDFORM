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
        assertEquals("HEAD", record.pullHead().tag);
        assertEquals(0, record.pullHead().level);
    }

    @Test
    public void testCopy() {
        final Cluster copy = record.copy();
        for (int i = 0; i < record.size(); i++) {
            assertEquals(record.get(i).tag, copy.get(i).tag);
        }
    }

    @Test
    public void testGet() throws EmptyClusterException {
        assertEquals("HEAD", record.getTag());
    }

    @Test
    public void testHasNext() {
        record.pullHead();
        record.pullCluster();
        assertTrue(record.hasNext());
        for (int i = 0; i < 13; i++) {
            record.pullCluster();
        }
        assertFalse(record.hasNext());
    }

    @Test
    public void testCluster() {
        assertEquals(35, record.pullCluster().size());
        record.reset();
        record.pullHead();
        assertEquals(17, record.pullCluster().size());
        assertEquals(1, record.pullCluster().size());
    }

    @Test
    public void testClusterInit() throws ParsingException {
        assertEquals("test", new Cluster("0 HEAD test").pullHead().value);
    }
}
