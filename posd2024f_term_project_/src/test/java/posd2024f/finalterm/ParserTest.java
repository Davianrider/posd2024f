package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.List;

public class ParserTest {

    @Test
    void testParserxml() {
        Parser parser = new Parser();
        String filePath = "data/config/EquipmentA/stateobserver.xml";
        Map<String, List<String>> result = parser.readXML(filePath);

        assertEquals("LotStart", result.get("Idle").get(0));
        assertEquals("LotArrived", result.get("Idle").get(1));
        assertEquals("LotEnd", result.get("Processing").get(0));
    }

}
