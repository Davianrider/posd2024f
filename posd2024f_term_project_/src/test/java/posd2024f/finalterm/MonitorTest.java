package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MonitorTest {
    
    @Test
    public void testGetInstance() {
        Monitor monitor1 = Monitor.getInstance();
        Monitor monitor2 = Monitor.getInstance();
        assertEquals(monitor1, monitor2);
        // EquipmentA A = new EquipmentA("equipment1");
        // EquipmentA B = new EquipmentA("equipment1");
        // assertEquals(A, B);
    }

    @Test
    public void testAddEquipmentAndGetById() {
        Monitor monitor = Monitor.getInstance();
        monitor.addEquipment("equipment1");
        assertEquals(EquipmentA.class, monitor.getEquipmentById("equipment1").getClass());
    }

}
