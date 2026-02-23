import java.util.*;

public class HostelFeeCalculator {
    private final BookingRepository repo;
    private final Map<Integer, RoomPricing> roomPricingMap;
    private final AddOnPricing addOnPricing;

    public HostelFeeCalculator(BookingRepository repo, Map<Integer, RoomPricing> roomPricingMap,
            AddOnPricing addOnPricing) {
        this.repo = repo;
        this.roomPricingMap = roomPricingMap;
        this.addOnPricing = addOnPricing;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        RoomPricing roomPricing = roomPricingMap.getOrDefault(req.roomType, new DeluxeRoomPricing());
        double base = roomPricing.getBasePrice();

        double add = 0.0;
        for (AddOn a : req.addOns) {
            add += addOnPricing.getPrice(a);
        }

        return new Money(base + add);
    }
}
