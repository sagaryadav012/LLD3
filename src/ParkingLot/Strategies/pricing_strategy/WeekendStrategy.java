package ParkingLot.Strategies.pricing_strategy;

import ParkingLot.Models.Slab;
import ParkingLot.Models.VehicleType;
import ParkingLot.Repositories.SlabRepository;
import ParkingLot.Utils.DateTimeUtils;

import java.util.Date;
import java.util.List;

public class WeekendStrategy implements CalculateFeesStrategy{
    private SlabRepository slabRepository;

    public WeekendStrategy(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }
    @Override
    public double calculateFees(Date entryDate, Date exitDate, VehicleType vehicleType) {
        // We should go via slab service
        List<Slab> slabs = slabRepository.getSlabsByVehicleType(vehicleType);
        int hours = DateTimeUtils.calcHours(entryDate, exitDate);

        double totalAmount = 0;
        for(Slab slab: slabs){
            if(hours >= slab.getStartHour() && slab.getEndHour() != -1){
                if(hours >= slab.getEndHour()){
                    totalAmount += (slab.getEndHour() - slab.getStartHour()) * slab.getPricePerHour();
                } else {
                    totalAmount += (hours - slab.getStartHour()) * slab.getPricePerHour();
                }
            } else if (slab.getEndHour() == -1) {
                totalAmount += (hours - slab.getStartHour()) * slab.getPricePerHour();
            }
            else {
                break;
            }
        }

        return totalAmount;
    }
}
