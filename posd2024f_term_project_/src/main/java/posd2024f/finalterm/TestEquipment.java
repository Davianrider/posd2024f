package posd2024f.finalterm;

public class TestEquipment extends Equipment {
    private boolean configurationLoaded = false;
    private boolean connected = false;
    private boolean equipmentSetUp = false;

    public TestEquipment(String equipmentId) {
        super(equipmentId);
    }

    @Override
    protected void loadConfiguration() throws Exception {
        configurationLoaded = true;
    }

    @Override
    protected void connectEquipment() {
        connected = true;
    }

    @Override
    protected void setupEquipment() {
        equipmentSetUp = true;
    }

    public boolean isConfigurationLoaded() {
        return configurationLoaded;
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isEquipmentSetUp() {
        return equipmentSetUp;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}

