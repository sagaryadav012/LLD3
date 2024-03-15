package ParkingLot.Repositories;

import ParkingLot.Models.Vehicle;
import ParkingLot.Models.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class VehicleRepository {
    private Map<Integer, Vehicle> map;

    public VehicleRepository(Map<Integer, Vehicle> map){
        this.map = map;
    }
    public VehicleRepository(){
        map = new HashMap<>();
    }

    private static int id = 1;
    public Vehicle createIfNotExist(String vehicleNumber, VehicleType vehicleType){
        for(Map.Entry<Integer, Vehicle> entry : map.entrySet()){
            Vehicle vehicle = entry.getValue();
            if(vehicle.getVehicleNumber().equals(vehicleNumber)){
                return vehicle;
            }
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setVehicleType(vehicleType);
        map.put(id++, vehicle);
        return vehicle;
    }
}
