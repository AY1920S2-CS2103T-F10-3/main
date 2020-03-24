package seedu.nova.model.plan;

import java.util.List;

/**
 * task container
 */
public interface Plan {
    void resetPlan();

    List<Task> getTaskList();

    boolean addTask(Task task);

    Task searchTask(TaskDetails taskDetails);

    boolean deleteTask(Task task);
}
