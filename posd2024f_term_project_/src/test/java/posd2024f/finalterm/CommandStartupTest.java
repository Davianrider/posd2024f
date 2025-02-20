package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandStartupTest {
    @Test
    void testCommandStartupExecute() throws Exception {
        String equipmentId = "testEquipment";
        Equipment equipment = new EquipmentA(equipmentId);
        CommandStartup command = new CommandStartup(equipment);
        command.execute();
        assertTrue(equipment.isRunning());
    }

}
