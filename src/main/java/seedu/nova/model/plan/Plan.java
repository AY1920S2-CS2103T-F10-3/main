package seedu.nova.model.plan;

import java.util.List;

import seedu.nova.model.event.Event;
import seedu.nova.model.schedule.Day;

/**
 * task container
 */
public interface Plan {
    void resetPlan();

    List<Task> getTaskList();

    boolean addTask(Task task);

    boolean deleteTask(Task task);

    Task searchTask(TaskDetails taskDetails);

    Event generateTaskEvent(Task task, Day day) throws ImpossibleTaskException;
}
