package ParkingLot.Services;

import ParkingLot.Exceptions.NoSpotAvailableException;
import ParkingLot.Models.Ticket;
import ParkingLot.Models.VehicleType;

public interface TicketService {
    Ticket generateTicket(int gateId, String vehicleNumber, String vehicleType) throws Exception;
    Ticket getTicketById(int ticketId);
}
