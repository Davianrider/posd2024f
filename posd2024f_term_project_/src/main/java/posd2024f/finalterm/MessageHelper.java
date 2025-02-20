package posd2024f.finalterm;

public class MessageHelper {
    private String equipmentId;
    private MessageStrategy strategy;

    public MessageHelper(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setStrategy(MessageStrategy strategy) {
        this.strategy = strategy;
    }

    public Message receiveMessage() {
        return strategy.receiveMessage(equipmentId);
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public MessageStrategy getStrategy() {
        return strategy;
    }
}
