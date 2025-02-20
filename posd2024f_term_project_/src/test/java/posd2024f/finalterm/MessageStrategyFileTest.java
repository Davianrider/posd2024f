package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageStrategyFileTest {
    @Test
    void testReceiveMessage() {
        MessageStrategyFile messageStrategyFile = new MessageStrategyFile();
        Message message = messageStrategyFile.receiveMessage("testEquipmentId");
        assertNull(message);
    }

}
