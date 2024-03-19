package ParkingLot.Models;

public class Slab extends  BaseModel{
    private VehicleType vehicleType;
    private int startHour;
    private int endHour;
    private double pricePerHour;

    public Slab(int id, VehicleType vehicleType, int startHour, int endHour, double pricePerHour) {
        this.setId(id);
        this.vehicleType = vehicleType;
        this.startHour = startHour;
        this.endHour = endHour;
        this.pricePerHour = pricePerHour;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }
}
