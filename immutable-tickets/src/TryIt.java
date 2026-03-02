import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demo showing immutability in action:
 * - Tickets are built via Builder
 * - "Updates" produce new instances; originals are unchanged
 * - External tag mutation has no effect on the ticket
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // 1. Create a ticket via builder
        IncidentTicket original = service.createTicket("TCK-1001", "reporter@example.com",
                "Payment failing on checkout");
        System.out.println("Created : " + original);

        // 2. "Update" through service — returns NEW instances, original is unchanged
        IncidentTicket assigned = service.assign(original, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nAssigned : " + assigned);
        System.out.println("Escalated: " + escalated);
        System.out.println("Original : " + original); // still the same!

        // 3. Try external mutation via leaked tags reference — has NO effect
        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\n[BUG] Tags were mutated from outside!");
        } catch (UnsupportedOperationException e) {
            System.out.println("\n[OK] Tags list is unmodifiable — external mutation blocked.");
        }
        System.out.println("Escalated tags still: " + escalated.getTags());
    }
}
