package posd2024f.finalterm;

public class LotStart implements EquipmentObserver {
    @Override
    public void action(String equipmentId, Message message) {
        System.out.println("Equipment " + equipmentId + " has started processing lot");
    }
}