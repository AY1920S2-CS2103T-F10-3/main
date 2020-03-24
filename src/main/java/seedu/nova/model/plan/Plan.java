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

    Task searchTask(TaskDetails taskDetails);

    boolean deleteTask(Task task);

    Event generateTaskEvent(Task task, Day day) throws ImpossibleTaskException;
}
