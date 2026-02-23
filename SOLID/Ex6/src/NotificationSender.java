/**
 * Base contract for all notification senders:
 * - send() must never throw exceptions for any valid Notification
 * - send() returns SendResult indicating success or failure
 * - Each sender uses the fields it needs (email field for email, phone for
 * SMS/WA)
 * - Subject may not be applicable to all channels — this is by design
 */
public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    public abstract SendResult send(Notification n);
}
