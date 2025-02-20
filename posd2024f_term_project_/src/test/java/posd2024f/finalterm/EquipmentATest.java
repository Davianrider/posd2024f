package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquipmentATest {
    @Test
    public void testEquipmentA() {
        EquipmentA equipment = new EquipmentA("A");
        assertEquals("A", equipment.getEquipmentId());
        assertTrue(equipment.isRunning());
    }

    @Test
    public void testEquipmentAStart() {
        EquipmentA equipment = new EquipmentA("A");
        Thread equipmentThread = new Thread(equipment);
        equipmentThread.start();
        assertTrue(equipment.isRunning());
    }

    @Test
    public void testEquipmentAStop() {
        EquipmentA equipment = new EquipmentA("A");
        Thread equipmentThread = new Thread(equipment);
        equipmentThread.start();
        equipment.stop();
        assertFalse(equipment.isRunning());
    }
}
