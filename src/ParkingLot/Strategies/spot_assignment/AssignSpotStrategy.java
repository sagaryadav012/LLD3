package ParkingLot.Strategies.spot_assignment;

import ParkingLot.Exceptions.NoSpotAvailableException;
import ParkingLot.Models.ParkingLot;
import ParkingLot.Models.Spot;
import ParkingLot.Models.VehicleType;

public interface AssignSpotStrategy {
    Spot assignSpot(VehicleType vehicleType, ParkingLot parkingLot) throws NoSpotAvailableException;
}
