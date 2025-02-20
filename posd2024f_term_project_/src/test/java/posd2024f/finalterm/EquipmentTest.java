package posd2024f.finalterm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquipmentTest {
    @Test
    public void testEquipment() {
        IllegalArgumentException equipment = assertThrows(IllegalArgumentException.class, () -> {
            new EquipmentA(null);
        });
        assertEquals("Illegal equipment id", equipment.getMessage());
    }

    @Test
    public void testStartup() throws Exception {
        // Arrange
        TestEquipment testEquipment = new TestEquipment("testEquipmentId");

        // Act
        testEquipment.startup();

        // Assert
        assertTrue(testEquipment.isConfigurationLoaded());
        assertTrue(testEquipment.isConnected());
        assertTrue(testEquipment.isEquipmentSetUp());
    }

    @Test
    public void testStop() {
        // Arrange
        TestEquipment testEquipment = new TestEquipment("testEquipmentId");

        // Act
        testEquipment.stop();

        // Assert
        assertFalse(testEquipment.isRunning());
    }



}
