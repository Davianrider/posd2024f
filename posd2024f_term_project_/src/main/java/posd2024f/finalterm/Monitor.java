package posd2024f.finalterm;

import java.util.ArrayList;

public class Monitor {
    private static Monitor instance;
    private ArrayList<Equipment> equipments;

    private Monitor() {
        equipments = new ArrayList<>();
    }

    public static synchronized Monitor getInstance() {
        if (instance == null) {
            instance = new Monitor();
        }
        return instance;
    }

    public void addEquipment(String equipmentId) {
        if (getEquipmentById(equipmentId) != null) {
            System.out.println("equipment already exists");
            return;
        }
        EquipmentA equipment = new EquipmentA(equipmentId);
        equipments.add(equipment);
    }

    public Equipment getEquipmentById(String equipmentId) {
        for (Equipment equipment : equipments) {
            if (equipment.getEquipmentId().equals(equipmentId)) {
                return equipment;
            }
        }
        return null;
    }

    public void sendActionToEquipment(String equipmentId, String action) throws Exception {
        Command command = null;
        Equipment equipment = getEquipmentById(equipmentId);

        if (equipment == null) {
            System.out.println("equipment not found");
            return;
        }

        if (action.equals("startup")) {
            command = new CommandStartup(equipment);
            command.execute();
        } else if (action.equals("shutdown")) {
            command = new CommandShutdown(equipment);
            command.execute();
        } else {
            System.out.println("invalid command");
        }
    }
}
