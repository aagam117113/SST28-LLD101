public class StandardAddOnPricing implements AddOnPricing {
    public double getPrice(AddOn addOn) {
        return switch (addOn) {
            case MESS -> 1000.0;
            case LAUNDRY -> 500.0;
            case GYM -> 300.0;
        };
    }
}
