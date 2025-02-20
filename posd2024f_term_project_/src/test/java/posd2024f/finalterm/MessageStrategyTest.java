package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageStrategyTest {
    @Test
    public void testMessageStrategy() {
        MessageStrategy messageStrategy = new MessageStrategyFile();
        Message message = messageStrategy.receiveMessage("testEquipmentId");
        assertNull(message);
    }

}
