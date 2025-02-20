package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquipmentMessageHelperTest {

    @Test
    public void testMessageHelper() {
        MessageHelper messageHelper = new MessageHelper("testEquipmentId");
        assertEquals("testEquipmentId", messageHelper.getEquipmentId());
    }

    @Test
    public void testMessageHelperSetStrategy() {
        MessageHelper messageHelper = new MessageHelper("testEquipmentId");
        MessageStrategy messageStrategy = new MessageStrategyFile();
        messageHelper.setStrategy(messageStrategy);
        assertEquals(messageStrategy, messageHelper.getStrategy());
    }

    @Test
    public void testMessageHelperReceiveMessage() {
        MessageHelper messageHelper = new MessageHelper("testEquipmentId");
        MessageStrategy messageStrategy = new MessageStrategyFile();
        messageHelper.setStrategy(messageStrategy);
        assertNull(messageHelper.receiveMessage());
    }
}
