package seedu.nova.model.schedule;

/**
 * Scheduler exception. Please don't use command exception as model has nothing to do with commands
 */
public class SchedulerException extends Exception {
    public SchedulerException(String str) {
        super(str);
    }
}
