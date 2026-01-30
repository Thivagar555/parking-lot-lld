package strategy;

public class HourlyPricingStrategy implements PricingStrategy {
    private static final double hourlyPrice=50.0;
    @Override
    public double calculatePrice(long durationInHours) {
        return  hourlyPrice*durationInHours;
    }
}
