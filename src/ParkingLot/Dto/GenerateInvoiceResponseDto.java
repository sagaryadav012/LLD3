package ParkingLot.Dto;

import ParkingLot.Models.Invoice;

public class GenerateInvoiceResponseDto {
    private Invoice invoice;
    private Response response;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
