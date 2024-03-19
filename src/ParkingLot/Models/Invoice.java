package ParkingLot.Models;

import java.util.Date;
import java.util.List;

public class Invoice extends BaseModel{
    private Ticket ticket;
    private Date exitTime;
    private double price;
    private List<InvoiceDetails> detailsList;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<InvoiceDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<InvoiceDetails> detailsList) {
        this.detailsList = detailsList;
    }
}
