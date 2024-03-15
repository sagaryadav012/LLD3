package ParkingLot.Models;

public enum VehicleType {
    BIKE,
    CAR,
    EV_CAR,
    TRUCK;

    public static VehicleType getTypeFromStr(String type){
        for (VehicleType value : VehicleType.values()) {
            if(type.equalsIgnoreCase(value.toString())){
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported vehicle type");
    }
}
