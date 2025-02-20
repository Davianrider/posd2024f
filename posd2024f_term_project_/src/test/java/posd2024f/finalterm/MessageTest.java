package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    void testMessageTitle() {
        Message message = new Message("Hello", "world");
        assertEquals("Hello", message.getTitle());
    }

    @Test
    void testMessageContent() {
        Message message = new Message("Hello", "world");
        assertEquals("world", message.getMessage());
    }
}
