package ParkingLot.Factories;

import ParkingLot.Repositories.SlabRepository;
import ParkingLot.Strategies.pricing_strategy.CalculateFeesStrategy;
import ParkingLot.Strategies.pricing_strategy.WeekDayStrategy;
import ParkingLot.Strategies.pricing_strategy.WeekendStrategy;

import java.util.Calendar;
import java.util.Date;

public class CalculateFeeStrategyFactory {
    private SlabRepository slabRepository;

    public CalculateFeeStrategyFactory(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }
    public CalculateFeesStrategy getCalculateFeesStrategy(Date exitDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(exitDate);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        boolean isWeekend = day == Calendar.SATURDAY || day == Calendar.SUNDAY;
       // isWeekend = true;
        CalculateFeesStrategy calculateFeesStrategy;
        if(isWeekend){
            calculateFeesStrategy = new WeekendStrategy(slabRepository);
        }else{
            calculateFeesStrategy = new WeekDayStrategy();
        }
        return calculateFeesStrategy;
    }
}
