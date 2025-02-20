package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class EquipmentObserverTest {
    
    @Test
    public void testEquipmentObserver() {
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        try{
        EquipmentObserver equipmentObserver = new EquipmentObserver() {
                @Override
                public void action(String equipmentId, Message message) {
                    System.out.println("EquipmentId: " + equipmentId + ", MessageTitle: " + message.getTitle() + ", MessageContent: " + message.getMessage());
                }
            };
            Message message = new Message("testTitle", "testMessage");
            equipmentObserver.action("testEquipmentId", message);

            String output = outputStream.toString().trim();
                assertEquals("EquipmentId: testEquipmentId, MessageTitle: testTitle, MessageContent: testMessage", output);
    
        } finally {
            System.setOut(originalOut);
        }
    }
}
