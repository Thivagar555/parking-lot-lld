package strategy;

public class DayPricingStrategy implements PricingStrategy {
    private static final double dayPrice = 1000.0;
    @Override
    public double calculatePrice(long day) {
        return dayPrice * day;
    }
}
