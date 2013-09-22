package gedformtest.implementation;

import com.undeadscythes.gedform.*;
import com.undeadscythes.gedform.exception.*;
import java.io.*;

/**
 * @author UndeadScythes
 */
@SuppressWarnings("serial")
public class TGC55C extends Transmission {
    public TGC55C() throws ParsingException {
        super(new File("src/test/resources/TGC55C.ged"));
    }
}
