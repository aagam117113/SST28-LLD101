package com.example.tickets;

/**
 * Service layer that creates and "updates" tickets.
 *
 * After refactor:
 * - Uses Builder exclusively to create tickets
 * - "Updates" return NEW ticket instances (immutability preserved)
 * - No validation here — it's all in Builder.build()
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return new IncidentTicket.Builder(id, reporterEmail, title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    /** Returns a NEW ticket with CRITICAL priority and ESCALATED tag. */
    public IncidentTicket escalateToCritical(IncidentTicket t) {
        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    /** Returns a NEW ticket with the given assignee. */
    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
