package seedu.nova.model.progresstracker;

import java.util.ArrayList;

/**
 * Handles list related methods for tasks
 */
public class PtTaskList {
    private final String listHeader = "Tasks for the week: \n";

    private ArrayList<PtTask> list;

    public PtTaskList() {
        list = new ArrayList<>();
    }

    public int getNumTask() {
        return list.size();
    }

    /**
     * Lists the tasks in the task list
     * @return String listing out the tasks
     */
    public String listTasks() {
        String str = "";

        for (int i = 0; i < list.size(); i++) {
            PtTask task = list.get(i);

            str = str + i + ") " + task.getTaskDesc().toString() + "\n";
        }
        return str;
    }
}
