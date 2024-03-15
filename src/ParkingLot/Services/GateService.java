package ParkingLot.Services;

import ParkingLot.Models.Gate;
import ParkingLot.Repositories.GateRepository;

public class GateService {
    GateRepository gateRepository;
    public GateService(GateRepository gateRepository){
        this.gateRepository = gateRepository;
    }

    public Gate getGateById(int gateId){
        return gateRepository.getGateById(gateId);
    }
}
