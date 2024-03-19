package ParkingLot.Services;

import ParkingLot.Exceptions.InvalidGateException;
import ParkingLot.Exceptions.InvalidTicketException;
import ParkingLot.Models.Invoice;
import ParkingLot.Models.Ticket;

public interface InvoiceService {
    Invoice generateInvoice(int ticketId, int gateId) throws InvalidTicketException, InvalidGateException;
}
