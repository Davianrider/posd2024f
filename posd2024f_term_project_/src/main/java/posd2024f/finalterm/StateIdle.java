package posd2024f.finalterm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.reflect.*;

public class StateIdle implements State {
    private List<EquipmentObserver> observers = new ArrayList<>();

    public StateIdle(Map<String, List<String>> map) throws Exception {

        // loop through the map and create an observer for Idle key
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey().equals("Idle")) {
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

        System.out.println("Receive message: " + message.getTitle());

        if (message.getTitle().equals("LotStart")) {
            equipment.setState(EquipmentA.GetStateProcessing());
            notifyObservers(equipment.getEquipmentId(), message);
        } else if (message.getTitle().equals("LotArrived")) {
            notifyObservers(equipment.getEquipmentId(), message);
        } else {
            System.out.println("Unexpected message received in Idle state");
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