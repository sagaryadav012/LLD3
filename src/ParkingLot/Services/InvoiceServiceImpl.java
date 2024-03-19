package ParkingLot.Services;

import ParkingLot.Factories.CalculateFeeStrategyFactory;
import ParkingLot.Exceptions.InvalidGateException;
import ParkingLot.Exceptions.InvalidTicketException;
import ParkingLot.Models.*;
import ParkingLot.Repositories.InvoiceRepository;
import ParkingLot.Strategies.pricing_strategy.CalculateFeesStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InvoiceServiceImpl implements InvoiceService{
    private TicketService ticketService;
    private GateService gateService;
    private CalculateFeeStrategyFactory factory;
    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(TicketService ticketService, GateService gateService, CalculateFeeStrategyFactory factory, InvoiceRepository invoiceRepository) {
        this.ticketService = ticketService;
        this.gateService = gateService;
        this.factory = factory;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice generateInvoice(int ticketId, int gateId) throws InvalidTicketException, InvalidGateException {
         /*
        1. Get ticket from DB, if ticket obj is null, throw Exception
        2. Get gate from DB, if gate obj is null or gate is an entry gate, throw an Exception
        3. Calculate charges via appropriate strategy
        4. Create invoice obj, store in DB and return
         */

        Ticket ticket = ticketService.getTicketById(ticketId);
        Gate gate = gateService.getGateById(gateId);

        if(ticket == null){
            throw new InvalidTicketException("Ticket is not present in DB");
        }
        if(gate.getGateType().equals(GateType.ENTRY)){
            throw new InvalidGateException("Invoice can't be created at Entry gate");
        }

        Date entryDate = ticket.getEntryTime();
        Date exitDate = new Date();
        VehicleType vehicleType = ticket.getVehicle().getVehicleType();
        CalculateFeesStrategy calculateFeesStrategy = factory.getCalculateFeesStrategy(exitDate);
        double totalAmount = calculateFeesStrategy.calculateFees(entryDate, exitDate, vehicleType);

        InvoiceDetails invoiceDetails = new InvoiceDetails();
        invoiceDetails.setName("Parking fee");
        invoiceDetails.setPrice(totalAmount);

        Invoice invoice = new Invoice();
        invoice.setDetailsList(Arrays.asList(invoiceDetails));
        invoice.setExitTime(exitDate);
        invoice.setTicket(ticket);
        invoice.setPrice(totalAmount);

        return invoiceRepository.insertInvoice(invoice);
    }
}
