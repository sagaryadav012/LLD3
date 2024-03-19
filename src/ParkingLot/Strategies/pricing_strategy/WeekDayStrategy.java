package ParkingLot.Strategies.pricing_strategy;

import ParkingLot.Models.VehicleType;
import ParkingLot.Utils.DateTimeUtils;

import java.util.Date;

public class WeekDayStrategy implements CalculateFeesStrategy{
    @Override
    public double calculateFees(Date entryDate, Date exitDate, VehicleType vehicleType) {
        int hours = DateTimeUtils.calcHours(entryDate, exitDate);
        return hours * 10;
    }
}
