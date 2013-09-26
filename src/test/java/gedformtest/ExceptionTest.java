package gedformtest;

import com.undeadscythes.gedform.exception.NoTagSetException;
import com.undeadscythes.gedform.exception.ParsingException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author UndeadScythes
 */
public class ExceptionTest {
    @Test
    public void testNoTagSetException() {
        try {
            throw new NoTagSetException("test");
        } catch (NoTagSetException ex) {
            assertTrue(ex.getMessage().endsWith("test."));
        }
    }

    @Test
    public void testParsingException() {
        try {
            throw new ParsingException("test");
        } catch (ParsingException ex) {
            assertEquals("test", ex.getMessage());
        }
    }
}
