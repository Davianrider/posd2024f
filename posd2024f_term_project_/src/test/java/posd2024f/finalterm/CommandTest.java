package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandTest {
    @Test
    void testCommandExecute() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream)); // 替換 System.out

        try {
            Command command = new Command() {
                @Override
                public void execute() {
                    System.out.println("Command executed");
                }
            };
            command.execute();

            // 捕獲並檢查輸出
            String output = outputStream.toString().trim();
            assertEquals("Command executed", output);
        } finally {
            System.setOut(originalOut); // 恢復原始的 System.out
        }
    }
}
