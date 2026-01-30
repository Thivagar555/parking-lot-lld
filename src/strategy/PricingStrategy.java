package strategy;

import model.Vehicle;

public interface PricingStrategy {
    double calculatePrice(long durationInHours);
}
