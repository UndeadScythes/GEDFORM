package gedformtest;

import com.undeadscythes.gedform.exception.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class ExceptionTest {
    @Test
    public void testNoTagSetException() {
        try {
            throw new NoTagSetException("test");
        } catch (NoTagSetException ex) {
            assertTrue("NoTag message", ex.getMessage().endsWith("test."));
        }
    }

    @Test
    public void testParsingException() {
        try {
            throw new ParsingException("test");
        } catch (ParsingException ex) {
            assertEquals("Parsing message", "test", ex.getMessage());
        }
    }

    @Test(expected = EmptyClusterException.class)
    public void testEmptyClusterException() throws EmptyClusterException {
            throw new EmptyClusterException();
    }
}
