package ParkingLot;

import ParkingLot.Controllers.InvoiceController;
import ParkingLot.Controllers.TicketController;
import ParkingLot.Dto.GenerateInvoiceRequestDto;
import ParkingLot.Dto.GenerateInvoiceResponseDto;
import ParkingLot.Dto.GenerateTicketRequestDto;
import ParkingLot.Dto.GenerateTicketResponseDto;
import ParkingLot.Factories.CalculateFeeStrategyFactory;
import ParkingLot.Models.*;
import ParkingLot.Repositories.*;
import ParkingLot.Services.*;
import ParkingLot.Strategies.spot_assignment.AssignSpotStrategy;
import ParkingLot.Strategies.spot_assignment.NearestFirstSpotAssignmentStrategy;

import java.util.*;

public class ParkingLotRunner {
    public static void main(String[] args) {
        Gate gate1 = new Gate();
        gate1.setId(1);
        gate1.setGateType(GateType.ENTRY);
        gate1.setOperator(new Operator());
        gate1.setName("1A");

        Gate gate2 = new Gate();
        gate2.setId(2);
        gate2.setGateType(GateType.EXIT);
        gate2.setOperator(new Operator());
        gate2.setName("4Z");

        Map<Integer, Gate> gateMap = new HashMap<>();
        gateMap.put(1, gate1);
        gateMap.put(2, gate2);


        // Create Spots
        List<Spot> spots = Arrays.asList(new Spot(VehicleType.CAR, SpotStatus.UN_OCCUPIED, "1A"),
                new Spot(VehicleType.BIKE, SpotStatus.UN_OCCUPIED, "3B"));

        // Create Sections and spots
        List<Section> sections = new ArrayList<>();
        Section section = new Section();
        section.setName("AA");
        section.setId(1);
        section.setSpots(spots);
        sections.add(section);

        // Create Floors
        List<Floor> floors = new ArrayList<>();
        Floor floor = new Floor();
        floor.setFloorNumber(1);
        floor.setFloorStatus(FloorStatus.OPERATIONAL);
        floor.setSections(sections);
        floor.setId(1);
        floors.add(floor);

        List<Gate> gates = new ArrayList<>();
        gates.add(gate1);
        gates.add(gate2);

        // Creating parkingLot
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setFloors(floors);
        parkingLot.setId(1);
        parkingLot.setGates(gates);

        Map<Integer, ParkingLot> parkingLotMap = new HashMap<>();
        parkingLotMap.put(1, parkingLot);

        Slab slab1 = new Slab(1,VehicleType.CAR, 0, 2, 20);
        Slab slab2 = new Slab(2, VehicleType.CAR, 2, 4, 25);
        Slab slab3 = new Slab(3, VehicleType.CAR, 4, 8, 30);
        Slab slab4 = new Slab(4, VehicleType.CAR, 8, -1, 40);

        Map<Integer,Slab> slabMap = new HashMap<Integer, Slab>(){{
            put(1, slab1);
            put(2, slab2);
            put(3, slab3);
            put(4, slab4);
        }};

        SlabRepository slabRepository = new SlabRepository(slabMap);
        InvoiceRepository invoiceRepository = new InvoiceRepository();



        GateRepository gateRepository = new GateRepository(gateMap);
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository(parkingLotMap);
        VehicleRepository vehicleRepository = new VehicleRepository();
        TicketRepository ticketRepository = new TicketRepository();


        GateService gateService = new GateServiceImpl(gateRepository);
        AssignSpotStrategy assignSpotStrategy = new NearestFirstSpotAssignmentStrategy();

        TicketService ticketService = new TicketServiceImpl(gateService, vehicleRepository, parkingLotRepository, assignSpotStrategy, ticketRepository);
        TicketController ticketController = new TicketController(ticketService);


        GenerateTicketRequestDto requestDto = new GenerateTicketRequestDto();
        requestDto.setGateId(1);
        requestDto.setVehicleNumber("TS29L4932");
        requestDto.setVehicleType(VehicleType.CAR.toString());

        GenerateTicketResponseDto responseDto = ticketController.generateTicket(requestDto);
        System.out.println(responseDto);

        int ticketId = responseDto.getTicket().getId();

        try {
            Thread.sleep(5000);
        } catch (Exception e){
            System.out.println("Exception while sleeping");
        }

        GenerateInvoiceRequestDto generateInvoiceRequestDto = new GenerateInvoiceRequestDto();
        generateInvoiceRequestDto.setTicketId(ticketId);
        generateInvoiceRequestDto.setGateID(gate2.getId());

        CalculateFeeStrategyFactory calculateFeesStrategyFactory = new CalculateFeeStrategyFactory(slabRepository);
        InvoiceService invoiceService = new InvoiceServiceImpl(ticketService, gateService, calculateFeesStrategyFactory, invoiceRepository);
        InvoiceController invoiceController = new InvoiceController(invoiceService);
        GenerateInvoiceResponseDto generateInvoiceResponseDto = invoiceController.generateInvoice(generateInvoiceRequestDto);
        System.out.println(generateInvoiceResponseDto);
        System.out.println(generateInvoiceResponseDto.getInvoice().getPrice());

    }
}
/*
-> First we create models
-> request goes to controllers first,
 */