package seedu.nova.model.plan;

import java.util.List;

import seedu.nova.model.util.Copyable;
import seedu.nova.model.event.Event;
import seedu.nova.model.schedule.timeunit.Week;
import seedu.nova.storage.JsonParsable;

/**
 * task container
 */
public interface Plan extends JsonParsable, Copyable<Plan> {
    String getName();

    List<Task> getTaskList();

    boolean addTask(Task task);

    boolean deleteTask(Task task);

    List<Event> getOrphanEventList();

    boolean addOrphanEvent(Event event);

    boolean removeOrphanEvent(Event event);

    // records every events created in a map
    List<Event> scheduleEvents(Week week);
}
