package posd2024f.finalterm;

public abstract class Equipment implements Runnable {
    protected String equipmentId;
    protected volatile boolean running = true;
    protected State currentState;

    public Equipment(String equipmentId) {
        if (equipmentId == null || equipmentId.isEmpty()) {
            throw new IllegalArgumentException("Illegal equipment id");
        }
        this.equipmentId = equipmentId;
    }

    public final void startup() throws Exception {
        loadConfiguration();
        connectEquipment();
        setupEquipment();
    }

    protected final void stop() {
        running = false;
    }

    protected abstract void loadConfiguration() throws Exception;

    protected abstract void connectEquipment();

    protected abstract void setupEquipment();

    protected void handleMessage(Message message) {
        currentState.handleMessage(this, message);
    };

    protected final void setState(State nextState) {
        this.currentState = nextState;
        System.out.println(equipmentId + " state change to " + nextState.getClass().getSimpleName());
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public final boolean isRunning() {
        return running;
    }
}
