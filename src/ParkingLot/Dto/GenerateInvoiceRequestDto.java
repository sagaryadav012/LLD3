package ParkingLot.Dto;

import ParkingLot.Models.Ticket;

public class GenerateInvoiceRequestDto {
    private int ticketId;
    private int gateID;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getGateID() {
        return gateID;
    }

    public void setGateID(int gateID) {
        this.gateID = gateID;
    }
}
