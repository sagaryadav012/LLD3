package ParkingLot.Models;

public class Gate extends BaseModel{
    private String name;
    private GateType gateType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GateType getGateType() {
        return gateType;
    }

    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }
}
