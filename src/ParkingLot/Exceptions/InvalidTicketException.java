package ParkingLot.Exceptions;

public class InvalidTicketException extends Exception{
    public InvalidTicketException(String msg){
        super(msg);
    }
}
