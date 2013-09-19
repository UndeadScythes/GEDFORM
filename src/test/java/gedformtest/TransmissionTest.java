package gedformtest;

import com.undeadscythes.gedform.*;
import gedformtest.implementation.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class TransmissionTest {
    private Transmission trans;

    @Before
    public void init() {
        trans = new Transmission(5);
        trans.add(ABCs.CLUSTER.copy());
        trans.add(ABCs.CLUSTER.copy());
        trans.add(ABCs.CLUSTER.copy());
        trans.add(ABCs.CLUSTER.copy());
        trans.add(ABCs.CLUSTER.copy());
    }

    @Test
    public void testCopy() {
        final Transmission copy = trans.copy();
        for (int i = 0; i < trans.size(); i++) {
            assertEquals("copy", trans.get(i).getTag(), copy.get(i).getTag());
        }
    }

    @Test
    public void testPull() {
        assertEquals("pullCluster", 6, trans.pullCluster().size());
    }

    @Test
    public void testHasNext() {
        trans.pullCluster();
        trans.pullCluster();
        trans.pullCluster();
        trans.pullCluster();
        assertTrue("hasNext-true", trans.hasNext());
        trans.pullCluster();
        assertFalse("hasNext-false", trans.hasNext());
    }
}
