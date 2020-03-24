package seedu.nova.model.plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Plan with definite tasks
 */
public class StudyPlan implements Plan {
    private HashMap<TaskDetails, Task> map;

    public StudyPlan() {
        resetPlan();
    }

    @Override
    public void resetPlan() {
        this.map = new HashMap<>();
    }

    @Override
    public List<Task> getTaskList() {
        return new ArrayList<>(map.values());
    }

    @Override
    public boolean addTask(Task task) {
        return map.put(task.details, task) == null;
    }

    @Override
    public Task searchTask(TaskDetails taskDetails) {
        return map.get(taskDetails);
    }

    @Override
    public boolean deleteTask(Task task) {
        return map.remove(task.details, task);
    }
}
