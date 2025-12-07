import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import by.r0manb.model.Region;


public class LabeledEnumTest {

    @Test
    public void testValidEnum(){
        {
            Region region = Region.fromLabel("Asia");
            assertSame(Region.ASIA, region);
        }
        {
            Region region = Region.fromLabel("Central America and the Caribbean");
            assertSame(Region.CENTRAL_AMERICA_N_CARIBBEAN, region);
        }
        {
            assertThrows(IllegalArgumentException.class, () -> Region.fromLabel("123"));
        }
    }
}
