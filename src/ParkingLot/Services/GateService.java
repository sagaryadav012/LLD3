package ParkingLot.Services;

import ParkingLot.Models.Gate;
import ParkingLot.Repositories.GateRepository;

public interface GateService {
    Gate getGateById(int gateId);
}
