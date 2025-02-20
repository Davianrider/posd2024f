package posd2024f.finalterm;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Monitor monitor = Monitor.getInstance();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = command.split(" ");
            if (parts.length == 2) {

                String action = parts[0];
                String equipmentId = parts[1];

                if (action.equals("create")) {
                    monitor.addEquipment(equipmentId);
                    continue;
                } else {
                    monitor.sendActionToEquipment(equipmentId, action);
                }
            } else {
                System.out.println("invalid format");
            }
        }

        scanner.close();
    }
}
