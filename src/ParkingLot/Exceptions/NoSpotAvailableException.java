package ParkingLot.Exceptions;

public class NoSpotAvailableException extends Exception{
    public NoSpotAvailableException(String message){
        super(message);
    }
}
