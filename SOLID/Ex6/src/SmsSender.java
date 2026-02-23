/**
 * SMS sender: uses phone and body fields.
 * Subject is intentionally not used — SMS is a body-only channel by design.
 */
public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public SendResult send(Notification n) {
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
        return SendResult.ok();
    }
}
