package seedu.nova.model.progresstracker;

/**
 *
 */
public class ProgressTracker {
    private Ip ip;

    public ProgressTracker() {
        this.ip = new Ip();
    }

    public Ip getIp() {
        return ip;
    }
}
