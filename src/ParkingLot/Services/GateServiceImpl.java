package ParkingLot.Services;

import ParkingLot.Models.Gate;
import ParkingLot.Repositories.GateRepository;

public class GateServiceImpl implements GateService{
    private GateRepository gateRepository;
    public GateServiceImpl(GateRepository gateRepository){
        this.gateRepository = gateRepository;
    }
    public Gate getGateById(int gateId){
        return gateRepository.getGateById(gateId);
    }
}
