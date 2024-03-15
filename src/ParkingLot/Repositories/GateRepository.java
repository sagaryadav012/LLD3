package ParkingLot.Repositories;

import ParkingLot.Models.Gate;

import java.util.HashMap;
import java.util.Map;

public class GateRepository {
    private Map<Integer, Gate> map;

    public GateRepository(Map<Integer, Gate> map){
        this.map = map;
    }

    public GateRepository(){
        this.map = new HashMap<>();
    }

    public Gate getGateById(int gateId){
        return map.get(gateId);
    }
}
