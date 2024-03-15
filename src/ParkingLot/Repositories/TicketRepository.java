package ParkingLot.Repositories;

import ParkingLot.Models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    Map<Integer, Ticket> map;

    public TicketRepository(Map<Integer, Ticket> map){
        this.map = map;
    }
    public TicketRepository(){
        this.map = new HashMap<>();
    }

    public static int id;
    public Ticket insertTicket(Ticket ticket){
        ticket.setId(id);
        map.put(id++, ticket);
        return ticket;
    }
}
