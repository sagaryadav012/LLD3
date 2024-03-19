package ParkingLot.Repositories;

import ParkingLot.Models.Slab;
import ParkingLot.Models.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlabRepository {
    private Map<Integer, Slab> map;

    public SlabRepository(Map<Integer, Slab> map){
        this.map = map;
    }
    public SlabRepository(){
        this.map = new HashMap<>();
    }

    public List<Slab> getSlabsByVehicleType(VehicleType vehicleType){
        List<Slab> slabs = new ArrayList<>();
        for(Map.Entry<Integer, Slab> entry : map.entrySet()){
            Slab slab = entry.getValue();
            if(slab.getVehicleType().equals(vehicleType)) slabs.add(slab);
        }
        slabs.sort((a,b) -> a.getStartHour() - b.getStartHour());
        return slabs;
    }
}
