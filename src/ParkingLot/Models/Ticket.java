package ParkingLot.Models;

import java.util.Date;

public class Ticket extends BaseModel{
    private Gate gate;
    private Vehicle vehicle;
    private Date entryTime;
    private Spot assignedSpot;

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Spot getAssignedSpot() {
        return assignedSpot;
    }

    public void setAssignedSpot(Spot assignedSpot) {
        this.assignedSpot = assignedSpot;
    }
}
