package posd2024f.finalterm;

public interface State {
    void handleMessage(Equipment equipment, Message message);
    void addObserver(EquipmentObserver observer);
}