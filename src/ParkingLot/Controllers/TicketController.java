package ParkingLot.Controllers;

import ParkingLot.Dto.GenerateTicketRequestDto;
import ParkingLot.Dto.GenerateTicketResponseDto;
import ParkingLot.Dto.Response;
import ParkingLot.Dto.ResponseStatus;
import ParkingLot.Exceptions.InvalidRequestException;
import ParkingLot.Models.Ticket;
import ParkingLot.Services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto){
        GenerateTicketResponseDto responseDto = new GenerateTicketResponseDto();

        Response response = new Response();
        try{
            if(requestDto.getGateId() < 0){
                throw new InvalidRequestException("Invalid gate id");
            }
            if(requestDto.getVehicleType() == null || requestDto.getVehicleType().equals("")){
                throw new InvalidRequestException("Vehicle Type is mandatory");
            }
        }
        catch (Exception e){
             response.setResponseStatus(ResponseStatus.FAILED);
             response.setError(e.getMessage());
        }

        try {
            Ticket ticket = ticketService.generateTicket(requestDto.getGateId(), requestDto.getVehicleNumber(), requestDto.getVehicleType());
            responseDto.setTicket(ticket);
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
        }
        responseDto.setResponse(response);

        return responseDto;
    }
}
