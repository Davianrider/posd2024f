package posd2024f.finalterm;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StateProcessing implements State {
    private List<EquipmentObserver> observers = new ArrayList<>();

    public StateProcessing(Map<String, List<String>> map) throws Exception {
        // loop through the map and create an observer for Processing key
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey().equals("Processing")) {
                for (String value : entry.getValue()) {
                    Class<?> lotEndClass = Class.forName("posd2024f.finalterm." + value);
                    Constructor<?> constructor = lotEndClass.getConstructor();
                    EquipmentObserver observer = (EquipmentObserver) constructor.newInstance();
                    addObserver(observer);
                }
            }
        }
    }

    @Override
    public void handleMessage(Equipment equipment, Message message) {
        if (message.getTitle().equals("LotEnd")) {
            equipment.setState(EquipmentA.GetStateIdle());
            notifyObservers(equipment.getEquipmentId(), message);
        } else {
            System.out.println("Unexpected message received in Processing state");
        }
    }

    public void addObserver(EquipmentObserver observer) {
        observers.add(observer);
    }

    // just notify corresponding observers
    private void notifyObservers(String equipmentId, Message message) {
        for (EquipmentObserver observer : observers) {
            if (observer.getClass().getSimpleName().equals(message.getTitle())) {
                observer.action(equipmentId, message);
            }
        }
    }
}
