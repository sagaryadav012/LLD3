package ParkingLot.Models;

public class Spot {
    private VehicleType vehicleType;
    private SpotStatus spotStatus;
    private String name;

    public Spot(VehicleType vehicleType, SpotStatus spotStatus, String name) {
        this.vehicleType = vehicleType;
        this.spotStatus = spotStatus;
        this.name = name;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public SpotStatus getSpotStatus() {
        return spotStatus;
    }

    public void setSpotStatus(SpotStatus spotStatus) {
        this.spotStatus = spotStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
