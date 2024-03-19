package ParkingLot.Controllers;

import ParkingLot.Dto.GenerateInvoiceRequestDto;
import ParkingLot.Dto.GenerateInvoiceResponseDto;
import ParkingLot.Dto.Response;
import ParkingLot.Dto.ResponseStatus;
import ParkingLot.Exceptions.InvalidGateException;
import ParkingLot.Exceptions.InvalidRequestException;
import ParkingLot.Exceptions.InvalidTicketException;
import ParkingLot.Models.Invoice;
import ParkingLot.Services.InvoiceService;

public class InvoiceController {
    public InvoiceService invoiceService;
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }
    public GenerateInvoiceResponseDto generateInvoice(GenerateInvoiceRequestDto requestDto){
        GenerateInvoiceResponseDto responseDto = new GenerateInvoiceResponseDto();

        try{
            if(requestDto.getTicketId() < 0){
                throw  new InvalidRequestException("Ticket id cannot be negative");
            }
            if(requestDto.getGateID() < 0){
                throw  new InvalidRequestException("Gate id cannot be negative");
            }
        } catch (Exception e){
            Response response = new Response();
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
            responseDto.setResponse(response);
            return responseDto;
        }

        try {
            Invoice invoice = invoiceService.generateInvoice(requestDto.getTicketId(), requestDto.getGateID());
            Response response = new Response();
            response.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setInvoice(invoice);
            responseDto.setResponse(response);
            return responseDto;
        } catch (Exception e) {
            Response response = new Response();
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
            responseDto.setResponse(response);
            return responseDto;
        }
    }
}
