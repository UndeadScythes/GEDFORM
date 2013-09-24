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
        assertEquals("test\na", new LineStruct("0 A test").cont("a").value);
    }

    @Test
    public void testConc() throws ParsingException {
        assertEquals("test a", new LineStruct("0 A test").conc("a").value);
    }
}
