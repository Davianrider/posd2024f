package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandShutdownTest {
    @Test
    void testCommandShutdownExecute() {
        String equipmentId = "testEquipment";
        Equipment equipment = new EquipmentA(equipmentId);
        CommandShutdown command = new CommandShutdown(equipment);
        command.execute();
        assertFalse(equipment.isRunning());
    }

}
