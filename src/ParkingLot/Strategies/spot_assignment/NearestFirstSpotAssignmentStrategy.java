package ParkingLot.Strategies.spot_assignment;

import ParkingLot.Exceptions.NoSpotAvailableException;
import ParkingLot.Models.*;

public class NearestFirstSpotAssignmentStrategy implements AssignSpotStrategy{
    @Override
    public Spot assignSpot(VehicleType vehicleType, ParkingLot parkingLot) throws NoSpotAvailableException {
        for (Floor floor : parkingLot.getFloors()) {
            if(floor.getFloorStatus().equals(FloorStatus.OPERATIONAL)){
                for (Section section : floor.getSections()) {
                    for (Spot spot : section.getSpots()) {
                        if(spot.getVehicleType().equals(vehicleType) && spot.getSpotStatus().equals(SpotStatus.UN_OCCUPIED)){
                            return spot;
                        }
                    }
                }
            }
        }
        throw new NoSpotAvailableException("No spot available for " + vehicleType);
    }
}
