package posd2024f.finalterm;

public class CommandStartup implements Command {
    private Equipment equipment;

    public CommandStartup(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public void execute() throws Exception {
        Thread equipmentThread = new Thread(equipment);
        equipmentThread.start();
        equipment.startup();
    }
}
