import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        Map<Integer, RoomPricing> roomPricingMap = Map.of(
                LegacyRoomTypes.SINGLE, new SingleRoomPricing(),
                LegacyRoomTypes.DOUBLE, new DoubleRoomPricing(),
                LegacyRoomTypes.TRIPLE, new TripleRoomPricing(),
                LegacyRoomTypes.DELUXE, new DeluxeRoomPricing());

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), roomPricingMap,
                new StandardAddOnPricing());
        calc.process(req);
    }
}
