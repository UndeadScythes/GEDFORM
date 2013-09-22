package gedformtest.deprecated;

import com.undeadscythes.gedform.*;
import com.undeadscythes.gedform.exception.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * @author UndeadScythes
 */
public class OldTransmissionTest {
    @Test
    public void testParse() throws ParsingException {
        @SuppressWarnings("deprecation")
        final Transmission trans = Transmission.parse(new File("src/test/resources/TGC55C.ged"));
        int count = 0;
        for (Cluster record : trans) {
            count += record.size();
        }
        assertEquals("size()", 1420, count);
        assertEquals("size()", 67, trans.size());
    }
}
