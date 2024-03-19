package ParkingLot.Strategies.pricing_strategy;

import ParkingLot.Models.VehicleType;

import java.util.Date;

public interface CalculateFeesStrategy {
    public double calculateFees(Date entryDate, Date exitDate, VehicleType vehicleType);
}
