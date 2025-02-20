package posd2024f.finalterm;

import java.util.Map;
import java.util.List;

public class EquipmentA extends Equipment {
    private static State stateIdle;
    private static State stateProcessing;

    public EquipmentA(String equipmentId) {
        super(equipmentId);
        System.out.println("Create equipment: " + equipmentId);
    }

    @Override
    public void run() {
        System.out.println("Start running equipment: " + equipmentId);

        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                MessageHelper messageHelper = new MessageHelper(equipmentId);
                messageHelper.setStrategy(new MessageStrategyFile());
                Message message = messageHelper.receiveMessage();
                if (message != null) {
                    handleMessage(message);
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(this.equipmentId + " was interrupted, stop execution");
                break;
            }
        }
        System.out.println("Equipment " + equipmentId + " has stopped");
    }

    @Override
    protected void loadConfiguration() throws Exception {
        System.out.println("Load configuration for equipment: " + equipmentId);

        String configFilePath = "data/config/" + this.getClass().getSimpleName() + "/stateobserver.xml";

        Parser parser = new Parser();
        Map<String, List<String>> result = parser.readXML(configFilePath);
        stateIdle = new StateIdle(result);
        stateProcessing = new StateProcessing(result);

        // 執行起來有點停滯感
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(this.equipmentId + " was interrupted");
        }
    }

    @Override
    protected void connectEquipment() {
        System.out.println("Connnect to equipment: " + equipmentId);

        // 執行起來有點停滯感
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(this.equipmentId + " was interrupted");
        }
    }

    @Override
    protected void setupEquipment() {
        System.out.println("Set up equipment: " + equipmentId);
        setState(stateIdle);
    }

    public static State GetStateIdle() {
        return stateIdle;
    }

    public static State GetStateProcessing() {
        return stateProcessing;
    }
}
