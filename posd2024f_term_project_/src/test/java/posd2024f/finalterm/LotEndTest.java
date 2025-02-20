package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LotEndTest {

    @Test
    public void testAction() {
        // Arrange
        LotEnd lotEnd = new LotEnd();
        String equipmentId = "A01";
        String lot = "lot001";
        String startTimeStr = "2024-12-24 13:12:01";
        String endTimeStr = "2024-12-24 14:20:01";
        String messageContent = String.format(
            "<?xml version=\"1.0\"?><message><lot>%s</lot><start_time>%s</start_time><end_time>%s</end_time></message>",
            lot, startTimeStr, endTimeStr
        );
        Message message = new Message("LotEnd", messageContent);
        
        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Act
        lotEnd.action(equipmentId, message);

        // Restore the original System.out
        System.setOut(originalOut);

        // Assert
        String expectedOutput = String.format(
            "Equipment %s has finished processing lot\n\tLot: %s\n\tStart Time: %s\n\tEnd Time: %s\n\tProcessing Time: %d minutes",
            equipmentId, lot, startTimeStr, endTimeStr, 68
        );
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }
}
