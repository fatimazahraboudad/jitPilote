package academy.jobintech.jitechpilot.enums;

/**
 * @author Yassine CHALH
 */
public enum TicketPriority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    CRITICAL("Critical");

    private final String priority;

    TicketPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return priority;
    }
}
