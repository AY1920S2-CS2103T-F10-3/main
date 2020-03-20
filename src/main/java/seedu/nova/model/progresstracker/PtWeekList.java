package seedu.nova.model.progresstracker;

import java.util.ArrayList;

/**
 * Handles week objects
 */
public class PtWeekList {
    private ArrayList<PtWeek> weekList;

    public PtWeekList() {
        weekList = new ArrayList<>();
    }

    /**
     * Adds week into list
     * @param week
     */
    public void addWeek(PtWeek week) {
        int num = week.getWeekNum();

        weekList.add(num - 1, week);
    }

    public PtWeek getWeek(int num) {
        if (weekList.size() >= num) {
            return weekList.get(num - 1);
        } else {
            return null;
        }
    }
}
