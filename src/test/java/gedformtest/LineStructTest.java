package gedformtest;

import com.undeadscythes.gedform.*;
import com.undeadscythes.gedform.exception.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class LineStructTest {
    @Test
    public void testCont() throws ParsingException {
        assertEquals("cont", "test\na", new LineStruct("0 A test").cont("a").value);
    }

    @Test
    public void testConc() throws ParsingException {
        assertEquals("conc", "testa", new LineStruct("0 A test").conc("a").value);
    }
}
