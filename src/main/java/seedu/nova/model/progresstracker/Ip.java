package seedu.nova.model.progresstracker;

/**
 * Represents individual project
 */
public class Ip extends Project {
    private PtWeekList weekList;

    public Ip() {
        weekList = new PtWeekList();
    }

    public PtWeekList getWeekList() {
        return weekList;
    }
}
