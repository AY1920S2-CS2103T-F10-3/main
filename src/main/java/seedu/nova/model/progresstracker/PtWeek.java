package seedu.nova.model.progresstracker;

/**
 * Represents a week
 */
public class PtWeek {
    public static final String MESSAGE_CONSTRAINTS = "Week must be a number and should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    private int weekNum;
    private PtTaskList taskList;

    public PtWeek(int num) {
        weekNum = num;
        taskList = new PtTaskList();
    }

    public int getWeekNum() {
        return weekNum;
    }

    public PtTaskList getTaskList() {
        return taskList;
    }
}
