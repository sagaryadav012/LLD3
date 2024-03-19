package ParkingLot.Services;

import ParkingLot.Exceptions.NoSpotAvailableException;
import ParkingLot.Models.*;
import ParkingLot.Repositories.ParkingLotRepository;
import ParkingLot.Repositories.TicketRepository;
import ParkingLot.Repositories.VehicleRepository;
import ParkingLot.Strategies.spot_assignment.AssignSpotStrategy;

import java.util.Date;

public class TicketServiceImpl implements TicketService{
    GateService gateService;
    VehicleRepository vehicleRepository;
    ParkingLotRepository parkingLotRepository;
    AssignSpotStrategy assignSpotStrategy;
    TicketRepository ticketRepository;

    public TicketServiceImpl(GateService gateService, VehicleRepository vehicleRepository, ParkingLotRepository parkingLotRepository, AssignSpotStrategy assignSpotStrategy, TicketRepository ticketRepository) {
        this.gateService = gateService;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.assignSpotStrategy = assignSpotStrategy;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket generateTicket(int gateId, String vehicleNumber, String vehicleType) throws Exception {

        /*
        1. Using gate id, get the gate object
        2. Do a createIfNotExists on the vehicle object
        3. Using strategy pattern, figure out an empty spot or throw an error
        4. create a ticket object and store it in db
         */

        Gate gate = gateService.getGateById(gateId);
        VehicleType type = VehicleType.getTypeFromStr(vehicleType);
        Vehicle vehicle = vehicleRepository.createIfNotExist(vehicleNumber, type);
        ParkingLot parkingLot = parkingLotRepository.getParkingLotByGateId(gateId);
        if(parkingLot == null){
            throw new Exception("Invalid Gate ID");
        }

        Spot spot = assignSpotStrategy.assignSpot(type, parkingLot);

        Ticket ticket = new Ticket();
        ticket.setGate(gate);
        ticket.setVehicle(vehicle);
        ticket.setAssignedSpot(spot);
        ticket.setEntryTime(new Date());

        return ticketRepository.insertTicket(ticket);
    }
    public Ticket getTicketById(int ticketId){
        return ticketRepository.getTicketById(ticketId);
    }
}
