package posd2024f.finalterm;

public class CommandShutdown implements Command {
    private Equipment equipment;

    public CommandShutdown(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public void execute() {
        equipment.stop();
    }
}