import by.r0manb.util.DateParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DateParserTest {

    @Test
    public void testValidDate(){
        assertEquals("2025-12-25", DateParser.parseString("12/25/2025").toString());
        assertEquals("2024-11-20", DateParser.parseString("20.11.2024").toString());
        assertEquals("2024-01-01", DateParser.parseString("1.01.2024").toString());
    }
}
