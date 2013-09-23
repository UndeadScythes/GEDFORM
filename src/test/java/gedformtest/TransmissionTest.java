package gedformtest;

import com.undeadscythes.gedform.*;
import com.undeadscythes.gedform.exception.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class TransmissionTest {
    private Transmission trans;

    @Before
    public void init() throws ParsingException {
        trans = new Transmission(new File("src/test/resources/TGC55C.ged"));
    }

    @Test
    public void testParse() throws ParsingException {
        int count = 0;
        for (Cluster record : trans) {
            count += record.size();
        }
        assertEquals("size()", 1420, count);
        assertEquals("size()", 67, trans.size());
    }

    @Test
    public void testCopy() throws EmptyClusterException {
        final Transmission copy = trans.copy();
        for (int i = 0; i < trans.size(); i++) {
            assertEquals("copy", trans.get(i).getTag(), copy.get(i).getTag());
        }
    }

    @Test
    public void testPull() {
        assertEquals("pullCluster", 35, trans.pullCluster().size());
    }

    @Test
    public void testHasNext() {
        for (int i = 0; i < 66; i++) {
            trans.pullCluster();
        }
        assertTrue("hasNext-true", trans.hasNext());
        trans.pullCluster();
        assertFalse("hasNext-false", trans.hasNext());
    }
}
